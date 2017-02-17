package com.yet.spring.core.logger;

import com.yet.spring.core.bean.Event;

import java.util.Collection;

/**
 * Created by RStreltsov on 14.02.2017.
 */
public class CombinedEventLogger implements EventLogger {
    private Collection loggers;

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

    public void methodTest() {
        //System.out.println("Run methodTest");
    }
}
