package com.yet.spring.core;

import com.yet.spring.core.bean.Client;
import com.yet.spring.core.logger.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by RStreltsov on 13.02.2017.
 */
public class App {

    private Client client;
    private EventLogger eventLogger;

    private void logEvent(String msg) {
        String message = msg.replaceAll(""+client.getId(), client.getFullName());
        //eventLogger.logEvent(message);
    }

    public App(Client client, EventLogger logger) {
        this.client = client;
        this.eventLogger = logger;
    }

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-app.xml");

        String[] cont = ctx.getBeanDefinitionNames();
        for(String name : cont) {
            System.out.println(name);
        }

        App app = ctx.getBean(App.class);

        app.logEvent("Some string 1");
        app.logEvent("Some string 2");
    }
}
