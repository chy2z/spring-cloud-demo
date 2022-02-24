package org.chy.carorder.config;

import org.chy.carorder.sys.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
/**
 * 设置过滤器的执行顺序
 * Created by chy on 2021/11/16.
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean myFilterBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new TimeFilter());
        //拦截所有请求
        filterRegistrationBean.addUrlPatterns("/*");
        //优先级1
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}