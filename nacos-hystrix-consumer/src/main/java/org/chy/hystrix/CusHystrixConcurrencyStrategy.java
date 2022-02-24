package org.chy.hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import org.chy.carorder.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;
import java.util.concurrent.Callable;

/**
 * Created by chy on 2021/8/21.
 */
@Component
public class CusHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {
    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @PostConstruct
    public void init() {
        HystrixConcurrencyStrategy hystrixConcurrencyStrategy = HystrixPlugins.getInstance().getConcurrencyStrategy();
        if (hystrixConcurrencyStrategy instanceof CusHystrixConcurrencyStrategy) {
            logger.warn("CusHystrixConcurrencyStrategy is exits!");
            return;
        }

        HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
        HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
        HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();
        HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance().getCommandExecutionHook();
        HystrixPlugins.reset();
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new CusHystrixConcurrencyStrategy());
        HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
        HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
        HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
        HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
    }


    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        logger.info("wrapCallable 当前线程ID：" + Thread.currentThread().getId() + "当前线程Name" + Thread.currentThread().getName());
        // 获取旧线程中传递ThreadLocal存储的值
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return new WrappedCallable<>(callable, requestAttributes);
    }

    static class WrappedCallable<T> implements Callable<T> {
        private final Callable<T> target;
        private final RequestAttributes requestAttributes;

        public WrappedCallable(Callable<T> target, RequestAttributes requestAttributes) {
            this.target = target;
            this.requestAttributes = requestAttributes;
        }

        @Override
        public T call() throws Exception {
            try {
                logger.info("WrappedCallable call 当前线程ID：" + Thread.currentThread().getId() + "当前线程Name" + Thread.currentThread().getName());
                // 在新的线程中传递ThreadLocal存储的值
                RequestContextHolder.setRequestAttributes(requestAttributes);
                return target.call();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        }
    }
}
