package org.chy;

import org.chy.interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class NacosHystrixConsumerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(NacosHystrixConsumerApp.class, args);
    }

    /**
     * RestTemplate的请求进行拦截的
     */
    @Autowired
    RestTemplateInterceptor restTemplateInterceptor;

    /**
     * netflix-hystrix 不支持对restTemplate的熔断降级
     *
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(restTemplateInterceptor));
        return restTemplate;
    }
}
