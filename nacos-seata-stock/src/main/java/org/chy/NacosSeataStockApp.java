package org.chy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 库存服务
 *
 * @author chy
 */
@ComponentScan("org.chy.stock")
@MapperScan("org.chy.stock.repository")
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication
public class NacosSeataStockApp
{
    public static void main( String[] args )
    {
       SpringApplication.run(NacosSeataStockApp.class, args);
    }
}