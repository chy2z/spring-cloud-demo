package org.chy.aop;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.netflix.hystrix.AbstractHystrixConfigBuilder;
import org.springframework.util.Assert;

import java.util.function.Function;

/**
 * @author chy
 * @Title: 自定义断路器工厂 替换 HystrixCircuitBreakerFactory
 * @Description:
 * @date 2022/11/9 23:46
 */
public class MyHystrixCircuitBreakerFactory extends
        CircuitBreakerFactory<HystrixCommand.Setter, org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory.HystrixConfigBuilder> {

    private Function<String, HystrixCommand.Setter> defaultConfiguration = id -> HystrixCommand.Setter
            .withGroupKey(
                    HystrixCommandGroupKey.Factory.asKey(getClass().getSimpleName()))
            .andCommandKey(HystrixCommandKey.Factory.asKey(id));

    @Override
    public void configureDefault(
            Function<String, HystrixCommand.Setter> defaultConfiguration) {
        this.defaultConfiguration = defaultConfiguration;
    }

    public org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory.HystrixConfigBuilder configBuilder(String id) {
        return new org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory.HystrixConfigBuilder(id);
    }

    @Override
    public MyHystrixCircuitBreaker create(String id) {
        Assert.hasText(id, "A CircuitBreaker must have an id.");
        HystrixCommand.Setter setter = getConfigurations().computeIfAbsent(id,
                defaultConfiguration);
        return new MyHystrixCircuitBreaker(setter);
    }

    public static class HystrixConfigBuilder
            extends AbstractHystrixConfigBuilder<HystrixCommand.Setter> {

        public HystrixConfigBuilder(String id) {
            super(id);
        }

        @Override
        public HystrixCommand.Setter build() {
            return HystrixCommand.Setter.withGroupKey(getGroupKey())
                    .andCommandKey(getCommandKey())
                    .andCommandPropertiesDefaults(getCommandPropertiesSetter());
        }

    }

}
