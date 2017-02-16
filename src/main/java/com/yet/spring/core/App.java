package com.yet.spring.core;

import com.yet.spring.core.bean.Client;
import com.yet.spring.core.bean.Event;
import com.yet.spring.core.logger.EventLogger;
import com.yet.spring.core.bean.EventType;
import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by RStreltsov on 13.02.2017.
 */
public class App //implements ApplicationListener
{

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
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
        app.logEvent(null, event2);

        Event event3 = ctx.getBean(Event.class);
        event3.setMsg("Some string 5555");
        app.logEvent(EventType.ERROR, event3);

        ctx.close();
    }

    /*
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("START!!!");
    }
    */
}
