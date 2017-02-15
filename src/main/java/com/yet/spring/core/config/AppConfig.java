package com.yet.spring.core.config;

import com.yet.spring.core.bean.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by RStreltsov on 15.02.2017.
 */
@Configuration
@PropertySource("classpath:client.properties")
//@EnableAspectJAutoProxy
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public Client client(){
        Client client = new Client();
        client.setId(Integer.valueOf(environment.getRequiredProperty("id")));
        client.setFullName(environment.getRequiredProperty("name"));
        client.setGreeting(environment.getProperty("greeting"));
        return client;
    }
}
