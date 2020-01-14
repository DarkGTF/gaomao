package com.evil.gaomao.system.entity;

import com.evil.gaomao.common.constant.LogType;
import lombok.Data;
import lombok.ToString;

/**
 * 日志
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2020-01-14
 * @since 1.0.0
 */
@Data
@ToString
public class Log {

    /**
     * uuid
     */
    private String id;
    /**
     * the type of log
     */
    private LogType logType;

    /**
     * the description of log type
     */
    private String content;
    /**
     * ip address
     */
    private String ipAddress;

    public Log(LogType logType,String content,String ipAddress) {
        this.content = content;
        this.logType = logType;
        this.ipAddress = ipAddress;
    }

}
