package com.github.bilak.axonframework.poc.infrastructure.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lvasek on 18/02/16.
 */
@Configuration
public class H2ConsoleConfiguration {

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        Map initParameters = new HashMap<>();
        initParameters.put("webAllowOthers", "true");
        registration.setInitParameters(initParameters);
        return registration;
    }
}
