package org.chy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 演示生产者注册到nacos
 *
 * http://localhost:8080/actuator
 * http://localhost:8080/actuator/nacosdiscovery
 * @author admin
 */
@ComponentScan("org.chy.order")
@MapperScan("org.chy.order.repository")
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication
public class NacosSeataOrderApp
{
    public static void main( String[] args )
    {
       SpringApplication.run(NacosSeataOrderApp.class, args);
    }
}