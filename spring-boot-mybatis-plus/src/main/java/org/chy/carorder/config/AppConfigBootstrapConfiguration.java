package org.chy.carorder.config;

import org.chy.carorder.config.propertysource.CusJsonPropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/6/9 0:09
 */
@Configuration(proxyBeanMethods = false)
public class AppConfigBootstrapConfiguration {
    @Bean
    public CusJsonPropertySourceLocator cusJsonPropertySourceLocator() {
        return new CusJsonPropertySourceLocator();
    }
}