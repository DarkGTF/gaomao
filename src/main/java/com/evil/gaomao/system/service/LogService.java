package com.evil.gaomao.system.service;

import com.evil.gaomao.system.entity.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 记录模块. Service
 * @author fangjiaxiaobai@gmail.com
 * @date 2020-01-14
 * @since 1.0.0
 */
@Service
@Slf4j
public class LogService {

    /**
     * todo 保存日志
     * @param logObj 日志
     */
    public void save(Log logObj){
        log.debug("保存日志到数据库 or other.....");
    }

}
