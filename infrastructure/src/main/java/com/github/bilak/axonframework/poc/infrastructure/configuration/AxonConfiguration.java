package com.github.bilak.axonframework.poc.infrastructure.configuration;

import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by lvasek on 27/07/15.
 */
@Configuration
public class AxonConfiguration {

    @Bean
    EntityManagerProvider entityManagerProvider() {
        return new ContainerManagedEntityManagerProvider();
    }

}
