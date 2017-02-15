package com.yet.spring.core.logger;

import com.yet.spring.core.bean.Event;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by RStreltsov on 14.02.2017.
 */
@Component
public class CombinedEventLogger implements EventLogger {

    @Resource(name = "combinedLoggers")
    private Collection loggers;

    public CombinedEventLogger() {
    }

    public Collection getLoggers() {
        return loggers;
    }

    public void setLoggers(Collection loggers) {
        this.loggers = loggers;
    }

    public CombinedEventLogger(Collection loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {

        //loggers.iterator().forEachRemaining(logger -> {logEvent(event);});

        for(Object log : loggers.toArray()) {
            EventLogger  logger = (EventLogger)log;
            logger.logEvent(event);
        }
        //EventLogger[] logger =
        //loggers.stream().forEach(logger -> logEvent(event));
    }
}
