package org.chy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 演示生产者注册到nacos
 *
 * http://localhost:8080/actuator
 * http://localhost:8080/actuator/nacosdiscovery
 */
@SpringBootApplication
public class NacosProviderApp
{
    public static void main( String[] args )
    {
       SpringApplication.run(NacosProviderApp.class, args);
    }
}