package com.microservices.product.service.togglz;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.mem.InMemoryStateRepository;
import org.togglz.spring.boot.actuate.autoconfigure.TogglzProperties;

import java.util.Map;

//@Configuration
//@EnableConfigurationProperties({AppTogglzProperties.class})
//@ConditionalOnProperty(prefix = "togglz", name = "enabled", matchIfMissing = true)
public class AppTogglzAutoConfiguration {

//    @Configuration
//    @ConditionalOnProperty(name = "qs.core.togglz.feature-togglz-service-uri")
    public static class StateRepositoryConfiguration {

        // TODO: Try removing the name it should work
        @Bean(name = "togglzServiceRestTemplate")
        public RestTemplate togglzServiceRestTemplate() {
            return new RestTemplate();
        }

        @Bean(name = "togglzStateRepository")
        @Order(1)
        public StateRepository togglzStateRepository(
                @Qualifier("togglzServiceRestTemplate") RestTemplate restTemplate,
                AppTogglzProperties togglzProperties) {

            return new AppTogglzStateRepository(restTemplate, togglzProperties.getFeatureTogglzServiceUri(), togglzProperties.isEnableCustomActivationStrategy());
        }

        @ConditionalOnProperty(name = "qs.core.togglz.enable-in-memory-togglz-as-fallback")
        @Bean(name = "inMemoryTogglzStateRepository")
        @Order(2)
        public StateRepository inMemoryTogglzStateRepository(TogglzProperties togglzProperties) {
            Map<String, TogglzProperties.FeatureSpec> featureSpecMap = togglzProperties.getFeatures();
            StateRepository stateRepository = new InMemoryStateRepository();
            for (String name: featureSpecMap.keySet()) {
                stateRepository.setFeatureState(featureSpecMap.get(name).state(name));
            }
            return stateRepository;
        }
    }
}
