package org.chy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@ComponentScan("org.chy.carorder")
@MapperScan("org.chy.carorder.mapper")
public class ShardingSphereApp {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereApp.class, args);
    }

}