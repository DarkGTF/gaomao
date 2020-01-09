package com.evil.gaomao.common.utils;

import com.evil.gaomao.common.constant.RedisKey;
import com.evil.gaomao.common.model.LoginBean;
import com.evil.gaomao.config.security.JwtProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Jwt token 工具类
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2019-11-27
 * @since 1.0.0
 */
@Component
@Slf4j
public class JwtUtil {

    private transient Clock clock = DefaultClock.INSTANCE;

    private JwtProperties jwtProperties;

    private RedisTemplate<Object, Object> redisManager;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 从Token中获取LoginBean
     *
     * @param token token
     * @return LoginBean
     * @throws JsonProcessingException json解析异常
     */
    public LoginBean getJwtUserFromToken(String token) throws JsonProcessingException {
        String subject = getClaimFromToken(token, Claims::getSubject);
        Map<String, Object> subjectMap = mapper.readValue(subject, Map.class);

        // 在token中存储了用户ID 用户名  用户状态
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername((String) subjectMap.get("username"));
        loginBean.setPhoneNumber((String) subjectMap.get("phoneNumber"));
        loginBean.setAlias((String) subjectMap.get("alias"));
        return loginBean;
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(clock.now());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    // 登陆校验成功后调用这个接口生成token下发
    public String generateToken(UserDetails userDetails, boolean isAppToken) {
        Map<String, Object> claims = new HashMap<>();

        try {
            String subject = mapper.writeValueAsString(userDetails);
            log.info("generateToken subject:{}", subject);
            String token = doGenerateToken(claims, subject);
            if (isAppToken) {
                redisManager.opsForValue().set(RedisKey.APP_TOKEN + token, token);
            } else {
                redisManager.opsForValue().set(RedisKey.WEB_TOKEN + token, token, jwtProperties.getExpiration(),
                        TimeUnit.SECONDS);
            }
            return token;
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot format json", e);
        }
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
        LoginBean loginBean = (LoginBean) userDetails;
        final LoginBean jwtUser = getJwtUserFromToken(token);
        return (
                jwtUser.getUsername().equals(loginBean.getUsername())
                        && !isTokenExpired(token));
    }

    private Date calculateExpirationDate(Date createdDate) {
        //过期时间1年
        return new Date(createdDate.getTime() + 1000 * 60 * 60 * 24 * 365 * 10);
    }

    @Autowired
    public void setJwtProperties(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Autowired
    public void setRedisManager(RedisTemplate<Object, Object> redisManager) {
        this.redisManager = redisManager;
    }
}
