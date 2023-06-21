package com.owner203.autoyours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutoyoursApplication {

    private static ConfigurableApplicationContext context;
    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {
        context = SpringApplication.run(AutoyoursApplication.class, args);
    }

}
