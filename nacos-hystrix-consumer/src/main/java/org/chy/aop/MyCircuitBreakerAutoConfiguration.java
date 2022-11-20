package org.chy.aop;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/11/9 23:45
 */
@Configuration(proxyBeanMethods = false)
public class MyCircuitBreakerAutoConfiguration {

    /**
     * 替换 HystrixCircuitBreakerFactory 类
     * @return 自定义 CircuitBreakerFactory
     */
    @Bean
    @ConditionalOnMissingBean(CircuitBreakerFactory.class)
    public CircuitBreakerFactory sentinelCircuitBreakerFactory() {
        return new MyHystrixCircuitBreakerFactory();
    }
}