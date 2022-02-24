package org.chy.carorder.controller;

import org.chy.carorder.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chy
 * @date 2021/7/14
 */
@RestController
@RequestMapping("/")
@RefreshScope
public class ProviderController {

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
    public String getConfig(@PathVariable String msg) {
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
