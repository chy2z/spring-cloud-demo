package org.chy;


import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.chy.sentinel.SentienlExceptionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosSentinelConsumerApp
{
    /**
     *  sentinel支持对restTemplate的服务调用使用sentinel方法.在构造
     *  RestTemplate对象的时候,只需要加载@SentinelRestTemplate即可
     *
     *  资源名:
     *       httpmethod:schema://host:port/path ：协议、主机、端口和路径
     *       httpmethod:schema://host:port ：协议、主机和端口
     *
     *
     *  restTemplate.getForObject("http://nacos-provider/getConfig/" + str, String.class);
     *  限流资源名称：GET:http://nacos-provider
     *
     *  SentinelRestTemplate起作用必须产生BlockException
     *  @see  com.alibaba.cloud.sentinel.custom.SentinelProtectInterceptor#intercept(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution)
     *
     *  @SentinelRestTemplate:
     *    异常降级
     *      fallback      : 降级方法
     *      fallbackClass : 降级配置类
     *    限流熔断
     *      blockHandler  : 熔断方法
     *      blockHandlerClass : 熔断配置类
     */
    @Bean
    @LoadBalanced
    @SentinelRestTemplate(blockHandler = "handleBlock",
            blockHandlerClass = SentienlExceptionUtil.class,
            fallback = "handleFallback",
            fallbackClass = SentienlExceptionUtil.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main( String[] args )
    {
        SpringApplication.run(NacosSentinelConsumerApp.class,args);
    }
}
