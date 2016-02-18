package com.github.bilak.axonframework.poc.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lvasek on 10/02/16.
 */

@Configuration
@EntityScan("org.axonframework")
public class PersistenceConfiguration extends JpaBaseConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        AbstractJpaVendorAdapter adapter = null;

        if (isActiveProfile("eclipselink"))
            adapter = new EclipseLinkJpaVendorAdapter();
        else if (isActiveProfile("hibernate"))
            adapter = new HibernateJpaVendorAdapter();

        return adapter;
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (isActiveProfile("eclipselink")) {
            map.put("eclipselink.weaving", detectWeavingMode());
        }
        return map;
    }

    private String detectWeavingMode() {
        return InstrumentationLoadTimeWeaver.isInstrumentationAvailable() ? "true" : "static";
    }

    private boolean isActiveProfile(String profileName) {
        return (Arrays.asList(env.getActiveProfiles()).contains(profileName));
    }
}
