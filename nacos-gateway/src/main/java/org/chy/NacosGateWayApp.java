package org.chy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosGateWayApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(NacosGateWayApp.class,args);
    }
}