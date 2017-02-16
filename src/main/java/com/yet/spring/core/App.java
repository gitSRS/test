package com.yet.spring.core;

import com.yet.spring.core.bean.Client;
import com.yet.spring.core.bean.Event;
import com.yet.spring.core.config.AppConfig;
import com.yet.spring.core.logger.EventLogger;
import com.yet.spring.core.bean.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by RStreltsov on 13.02.2017.
 */
@Service
public class App
{
    @Autowired
    private Client client;

    @Autowired
    @Qualifier("cacheFileEventLogger")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;
/*
    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }
*/

    private void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);

        if(logger == null) {
            logger = defaultLogger;
            System.out.println("Yes");
        }

        String message = event.getMessage();
        event.setMessage(message.replaceAll(""+client.getId(), client.getFullName()));
        System.out.println("app logMes mess="+event.getMessage());
        logger.logEvent(event);
    }

    public static void main(String[] args){
        //ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.scan("com.yet.spring.core");
        ctx.refresh();

        String[] cont = ctx.getBeanDefinitionNames();
        for(String name : cont) {
            System.out.println(name);
        }

        Client cli = ctx.getBean(Client.class);
        System.out.println("id = "+cli.getId());
        cli.setId(2);
        System.out.println("id_ = "+cli.getId());

        App app = ctx.getBean(App.class);

        Event event = ctx.getBean(Event.class);
        event.setMessage("Some string 4444");
        System.out.println("mess="+event.getMessage());
        app.logEvent(EventType.ERROR, event);

        event = ctx.getBean(Event.class);
        event.setMessage("Some string 5555");
        System.out.println("mess="+event.getMessage());
        app.logEvent(null, event);

        event = ctx.getBean(Event.class);
        event.setMessage("Some string 1");
        app.logEvent(EventType.INFO, event);

        ctx.close();
    }
}
