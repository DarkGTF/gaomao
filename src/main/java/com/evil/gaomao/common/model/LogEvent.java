package com.evil.gaomao.common.model;

import com.evil.gaomao.common.constant.LogType;
import com.evil.gaomao.system.entity.Log;
import org.springframework.context.ApplicationEvent;

/**
 * 日志 事件
 *
 * @author fangjiaxiaobai@gmail.com
 * @date 2020-01-14
 * @since 1.0.0
 */
public class LogEvent extends ApplicationEvent {

    private Log log;

    public LogEvent(Object source, Log logParam) {
        super(source);
        this.log = logParam;
    }

    public LogEvent(Object source, LogType logType, String content) {
        this(source, new Log(logType, content, ""));
    }

    public Log getLog() {
        return log;
    }
}
