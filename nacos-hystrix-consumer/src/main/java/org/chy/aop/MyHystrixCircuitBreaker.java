package org.chy.aop;

import com.netflix.hystrix.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author chy
 * @Title: 自定义断路器 替换 HystrixCircuitBreaker
 * @Description:
 * @date 2022/11/9 23:24
 */
public class MyHystrixCircuitBreaker implements CircuitBreaker {
    final static Logger logger = LoggerFactory.getLogger(MyHystrixCircuitBreaker.class);

    private HystrixCommand.Setter setter;

    public MyHystrixCircuitBreaker(HystrixCommand.Setter setter) {
        this.setter = setter;
    }

    @Override
    public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {

        HystrixCommand<T> command = new HystrixCommand<T>(setter) {
            @Override
            protected T run() throws Exception {
                return toRun.get();
            }

            @Override
            protected T getFallback() {
                Throwable throwable = getExecutionException();
                logger.error("MyHystrixCircuitBreaker exception",throwable);
                return fallback.apply(throwable);
            }
        };
        return command.execute();
    }

}