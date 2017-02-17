package com.yet.spring.core;

import com.yet.spring.core.bean.Client;
import com.yet.spring.core.bean.Event;
import com.yet.spring.core.logger.EventLogger;
import com.yet.spring.core.bean.EventType;
import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by RStreltsov on 13.02.2017.
 */
public class App //implements ApplicationListener
{

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App() {
        //System.out.println("Constructor init");
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<EventType, EventLogger> getLoggers() {
        return loggers;
    }

    public void setLoggers(Map<EventType, EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }



    public EventLogger getDefaultLogger() {
        return defaultLogger;
    }

    private void logEvent(EventType type, Event event) {
        EventLogger logger = getLoggers().get(type); //loggers.get(type);

        if(logger == null) {
            logger = getDefaultLogger(); //defaultLogger;
        }

        String message = event.getMsg();
        //event.setMsg(message.replaceAll(""+client.getId(), client.getFullName()));
        event.setMsg(message.replaceAll(""+getClient().getId(), getClient().getFullName()));

        logger.logEvent(event);
    }

    public void getStatistic(){
        System.out.println("getStatistic is run");
    }

    public static void main(String[] args){
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-app.xml");

        /*
        String[] cont = ctx.getBeanDefinitionNames();
        for(String name : cont) {
            System.out.println(name);
        }
        */

        App app = ctx.getBean(App.class);


        Event event = ctx.getBean(Event.class);
        event.setMsg("Some string 1");
        app.logEvent(EventType.INFO, event);

        Event event2 = ctx.getBean(Event.class);
        event2.setMsg("Some string 4444");
        //mp.get(EventType.ERROR).logEvent(event2);
        app.logEvent(null, event2);
        app.logEvent(EventType.ERROR, event2);
/*
        Event event3 = ctx.getBean(Event.class);
        event3.setMsg("Some string 5555");
        app.logEvent(EventType.ERROR, event3);
*/
        app.getStatistic();
        ctx.close();
    }

    /*
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("START!!!");
    }
    */
}
