package org.chy.health.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("healthProperties")
@ConfigurationProperties("chy.app.health")
public class HealthProperties {
    public static final String CACHE_NAME = "healthProperties";
    public static final String PREFIX = "chy.app.health";
    private boolean enabled = true;
    private String response = "PONG";

    public HealthProperties() {
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}