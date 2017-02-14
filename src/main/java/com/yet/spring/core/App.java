package com.yet.spring.core;

import com.yet.spring.core.bean.Client;
import com.yet.spring.core.logger.EventLogger;
import com.yet.spring.core.logger.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by RStreltsov on 13.02.2017.
 */
public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, Map<EventType, EventLogger> loggers, EventLogger defaultLogger) {
        this.client = client;
        this.loggers = loggers;
        this.defaultLogger = defaultLogger;
    }

    private void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if(logger == null) {
            logger = defaultLogger;
        }

        String message = event.getMsg();
        event.setMsg(message.replaceAll(""+client.getId(), client.getFullName()));

        logger.logEvent(event);
    }

    public static void main(String[] args){
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-app.xml");

        String[] cont = ctx.getBeanDefinitionNames();
        for(String name : cont) {
            System.out.println(name);
        }

        App app = ctx.getBean(App.class);
        Event event = ctx.getBean(Event.class);
        event.setMsg("Some string 1");
        app.logEvent(EventType.INFO, event);

        Event event2 = ctx.getBean(Event.class);
        event2.setMsg("Some string 4444");
        app.logEvent(null, event);

        //app.logEvent("Some string 2");
        ctx.close();
    }
}
