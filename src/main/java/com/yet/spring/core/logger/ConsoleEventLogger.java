package com.yet.spring.core.logger;

import com.yet.spring.core.bean.Event;

/**
 * Created by RStreltsov on 13.02.2017.
 */
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event);
    }

    public ConsoleEventLogger() {
    }

    public void methodTest() {
        //System.out.println("Run methodTest");
    }
}
