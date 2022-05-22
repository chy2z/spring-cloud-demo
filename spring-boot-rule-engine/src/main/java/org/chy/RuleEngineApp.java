package org.chy;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/**
 * 上来只扫描本项目包
 */
@ComponentScan("org.chy.carorder")
@MapperScan("org.chy.carorder.mapper")
public class RuleEngineApp {

    public static void main(String[] args) {
        SpringApplication.run(RuleEngineApp.class, args);
    }

}