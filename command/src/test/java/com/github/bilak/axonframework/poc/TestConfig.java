package com.github.bilak.axonframework.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by lvasek on 06/08/15.
 */
@EnableAutoConfiguration
@SpringBootApplication
public class TestConfig {

    public static void main(String[] args) {
        SpringApplication.run(TestConfig.class);
    }
}
