package com.yet.spring.core.logger;

import com.yet.spring.core.bean.Event;
import org.springframework.stereotype.Component;

/**
 * Created by RStreltsov on 13.02.2017.
 */
@Component
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event);
    }

    public ConsoleEventLogger() {
    }
}
