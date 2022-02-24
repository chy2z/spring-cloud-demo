package org.chy.carorder.controller;

import org.chy.carorder.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 *
 * @author chy
 * @date 2021/7/14
 */
@RestController
@RequestMapping("/")
@RefreshScope
public class ProviderController {
    private final static Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    AppConfig appConfig;

    @Value("${appName:test}")
    public String appName;

    /**
     * http://127.0.0.1:8080/getNacosConfig
     * @return
     */
    @GetMapping(value = "/getNacosConfig")
    public String getNacosConfig() {
        return appConfig.toString();
    }

    /**
     * http://127.0.0.1:8080/getAppName
     * @return
     */
    @GetMapping(value = "/getAppName")
    public String getAppName() {
        return appName;
    }


    /**
     * 测试获取配置
     * http://localhost:8080/getConfig/test
     *
     * @return
     */
    @GetMapping("/getConfig/{msg}")
    public String getConfig(@PathVariable String msg, HttpServletRequest request) {
        //获取所有请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            //根据名称获取请求头的值
            String value = request.getHeader(name);
            logger.info("request header:{}---value:{}", name, value);
        }
        return "privider:" + msg;
    }

    /**
     * 测试获取配置
     * http://localhost:8080/version
     *
     * @return
     */
    @GetMapping("/version")
    public String getVersion() {
        return "vesion:1.0.0" ;
    }
}
