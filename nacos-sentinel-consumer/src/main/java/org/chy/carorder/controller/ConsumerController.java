package org.chy.carorder.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.chy.services.NacosProviderFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLClientInfoException;

/**
 * Created by chy on 2021/7/16.
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NacosProviderFeignService nacosProviderFeignService;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * http://localhost:9091/rest/test
     *
     * 测试 @SentinelResource 注解
     *
     * blockHandler 限流
     * fallback 失败
     *
     * @see  SentinelResourceAspect#invokeResourceWithSentinel(org.aspectj.lang.ProceedingJoinPoint)
     *
     * 利用 aop 实现，得关闭拦截器(spring.cloud.sentinel.filter.enabled)否则 blockHandler 触发不了，被拦截器
     * @see  com.alibaba.cloud.sentinel.custom.SentinelProtectInterceptor 拦截先执行
     *
     * @param str
     * @return
     */
    @SentinelResource(value = "rest",blockHandler = "restBlockHandler",fallback = "restFallback",
            exceptionsToIgnore={SQLClientInfoException.class})
    @GetMapping("/rest/{str}")
    public String rest(@PathVariable String str) {
        return restTemplate.getForObject("http://nacos-provider/getConfig/" + str,
                String.class);
    }


    /**
     * http://localhost:9091/restGlobal
     *
     * 测试@SentinelRestTemplate注解
     *
     * @param str
     * @return
     */
    @GetMapping("/restGlobal/{str}")
    public String restGlobal(@PathVariable String str) {
        return restTemplate.getForObject("http://nacos-provider/getConfig/" + str,
                String.class);
    }

    /**
     * Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
     *
     * 访问非限流降级异常，执行失败兜底
     *
     * @param str
     * @return
     */
    public String restFallback(@PathVariable String str,Throwable e) {
        return "{\"restFallback\":\""+str+"\",\"exception\":\""+e.getClass()+"\"}";
    }

    /**
     * blockHandler 函数，函数签名与原函数一致以及最后加一个 BlockException 类型的参数.
     *
     * 访问限流降级异常时，执行
     *
     * @param str
     * @param e
     * @return
     */
    public String restBlockHandler(@PathVariable String str, BlockException e) {
        return "restBlockHandler" + str;
    }


    /**
     * http://localhost:9091/feign-version
     *
     * @return
     */
    @GetMapping("/feign-version")
    public String version() {
        return nacosProviderFeignService.version();
    }

    /**
     * http://localhost:9091/feign-fetchConfig/test
     *
     * @param str
     * @return
     */
    @GetMapping("/feign-fetchConfig/{str}")
    public String fetchConfig(@PathVariable String str) {
        return nacosProviderFeignService.fetchConfig(str);
    }

    /**
     * http://localhost:9091/discovery/services/nacos-provider
     * http://localhost:9091/discovery/services/nacos-hystrix-consumer
     *
     * @param service
     * @return
     */
    @GetMapping("/discovery/services/{service}")
    public Object client(@PathVariable String service) {
        return discoveryClient.getInstances(service);
    }

    /**
     * http://localhost:9091/discovery/services
     *
     * @return
     */
    @GetMapping("/discovery/services")
    public Object services() {
        return discoveryClient.getServices();
    }
}