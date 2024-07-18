package com.microservices.core.togglz;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "qs.core.togglz")
public class AppTogglzProperties {

    private String featureTogglzServiceUri;
    private boolean enableInMemoryTogglzAsFallback;
    private boolean enableCustomActivationStrategy;

    @Getter
    @Setter
    public static class Auth {
        private String userName;
        private String password;
    }
}
