package com.yet.spring.core.logger;

import com.yet.spring.core.Event;

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

    }
}
