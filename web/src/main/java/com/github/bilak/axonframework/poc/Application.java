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
@PropertySource(value = { "classpath:application.properties","classpath:infrastructure.properties" })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args   );
    }
}
