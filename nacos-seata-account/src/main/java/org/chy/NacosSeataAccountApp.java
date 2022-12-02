package org.chy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 演示生产者注册到nacos
 *
 * http://localhost:8080/actuator
 * http://localhost:8080/actuator/nacosdiscovery
 * @author admin
 */
@ComponentScan("org.chy.account")
@MapperScan("org.chy.account.repository")
@SpringBootApplication
public class NacosSeataAccountApp
{
    public static void main( String[] args )
    {
       SpringApplication.run(NacosSeataAccountApp.class, args);
    }
}