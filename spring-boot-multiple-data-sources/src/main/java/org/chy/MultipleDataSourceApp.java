package org.chy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
/**
 * @MapperScan 有2种
 *   1) mybatis原生 ：org.mybatis.spring.annotation
 *   2）tk.mybatis:tk.mybatis.spring.annotation.MapperScan 扫描集成 Mapper<CarOrder> , 并且第2种兼容第一种
 */
@MapperScan("org.chy.carorder.mapper")
public class MultipleDataSourceApp {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDataSourceApp.class, args);
    }

}