package org.chy.health;

import org.chy.health.endpoint.HealthCheckV1Controller;
import org.chy.health.support.HealthProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({HealthProperties.class})
@Configuration
@EnableConfigurationProperties({HealthProperties.class})
public class AppHealthAutoConfiguration {
    private final HealthProperties healthProperties;

    public AppHealthAutoConfiguration(HealthProperties healthProperties) {
        this.healthProperties = healthProperties;
    }

    @Bean
    @ConditionalOnProperty(
            name = {"chy.app.health.enabled"},
            havingValue = "true",
            matchIfMissing = true
    )
    public HealthCheckV1Controller checkV1Controller() {
        return new HealthCheckV1Controller(this.healthProperties);
    }
}