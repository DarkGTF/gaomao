package com.evil.gaomao.config.security.filter;

import com.evil.gaomao.common.constant.RedisKey;
import com.evil.gaomao.common.model.LoginBean;
import com.evil.gaomao.common.utils.JwtUtil;
import com.evil.gaomao.config.security.JwtProperties;
import com.evil.gaomao.config.security.UrlFilterProperties;
import com.evil.gaomao.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Jwt 登录拦截器
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-11-27
 * @since 1.0.0
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtProperties jwtProperties;

    private JwtUtil jwtUtil;

    private UserService userDetailsService;

    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * url拦截 白名单
     */
    private UrlFilterProperties urlFilterProperties;

    private OrRequestMatcher orRequestMatcher;

    @PostConstruct
    public void init() {
        List<RequestMatcher> matchers = urlFilterProperties.getUrls().stream()
                .map(AntPathRequestMatcher::new)
                .collect(Collectors.toList());
        orRequestMatcher = new OrRequestMatcher(matchers);
    }

    /**
     * 判断是否对url拦截
     *
     * @param request 请求
     * @return ture/false
     * @throws ServletException 异常
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return orRequestMatcher.matches(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(jwtProperties.getTokenHeader());
        if (token == null || token.length() == 0) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean isAppToken = false;
        if (token.startsWith(RedisKey.APP_TOKEN)) {
            isAppToken = true;
        }

        token = token.replace(isAppToken ? RedisKey.APP_TOKEN : RedisKey.WEB_TOKEN, "");

        LoginBean loginBean = null;

        try {
            loginBean = jwtUtil.getJwtUserFromToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (loginBean != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (!isAppToken) {
                if (redisTemplate.opsForValue().setIfAbsent(RedisKey.WEB_TOKEN + token, token)) {
                    throw new AccountExpiredException("登录信息已经过期或已经退出登录，请重新登录！");
                } else {
                    redisTemplate.opsForValue().getOperations().expire(RedisKey.WEB_TOKEN + token,
                            jwtProperties.getExpiration(), TimeUnit.SECONDS);
                }
            }

            UserDetails user = userDetailsService.loadUserByUsername(loginBean.getAlias());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                    user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            log.debug("authorizated user '{}', setting security context", user.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    @Autowired
    public void setJwtProperties(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setUserDetailsService(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setUrlFilterProperties(UrlFilterProperties urlFilterProperties) {
        this.urlFilterProperties = urlFilterProperties;
    }
}
