package com.github.bilak.axonframework.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


/**
 * Created by lvasek on 10/08/15.
 */
@SpringBootApplication
@EnableAutoConfiguration
@PropertySource(value = { "application.properties","classpath:infrastructure.properties" }, ignoreResourceNotFound = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args   );
    }
}
