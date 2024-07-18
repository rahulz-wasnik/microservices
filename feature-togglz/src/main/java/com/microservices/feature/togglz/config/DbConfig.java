package com.microservices.feature.togglz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.user.UserProvider;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    public FeatureManager featureManager(StateRepository stateRepository, UserProvider userProvider) {
        return new FeatureManagerBuilder()
                .featureEnum(FeatureFlags.class)
                .stateRepository(stateRepository)
                .build();
    }

    @Bean
    public StateRepository stateRepository(DataSource dataSource) {
        return JDBCStateRepository.newBuilder(dataSource).build();
    }
}
