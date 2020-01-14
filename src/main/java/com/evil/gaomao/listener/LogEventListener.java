package com.evil.gaomao.listener;

import com.evil.gaomao.common.model.LogEvent;
import com.evil.gaomao.system.entity.Log;
import com.evil.gaomao.system.service.LogService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author fangjiaxiaobai@gmail.com
 * @date 2020-01-14
 * @since 1.0.0
 */
@Component
public class LogEventListener {

    private LogService logService;

    public LogEventListener(LogService logService) {
        this.logService = logService;
    }

    @EventListener
    @Async
    public void onApplicationEvent(LogEvent logEvent) {
        Log log = logEvent.getLog();
        logService.save(log);
    }

}
