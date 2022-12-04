package org.chy.account.controller;

import org.chy.account.config.AppConfig;
import org.chy.account.response.ResponseEntityDTO;
import org.chy.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 *
 * @author chy
 * @date 2021/7/14
 */
@RestController
@RefreshScope
public class AccountController {
    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private AccountService accountService;

    /**
     * http://127.0.0.1:8080/getNacosConfig
     * @return
     */
    @GetMapping(value = "/account/getNacosConfig")
    public String getNacosConfig() {
        return appConfig.toString();
    }

    /**
     * 测试获取配置
     * http://localhost:8080/version
     *
     * @return
     */
    @GetMapping("/account/version")
    public String getVersion() {
        return "vesion:1.0.0" ;
    }

    /**
     * 减少金额
     *
     * http://127.0.0.1:8080/reduce?userId=1001&money=1.0
     *
     * @param userId 用户id
     * @param money 金额
     * @return 结果
     */
    @PostMapping("/account/reduce")
    public ResponseEntityDTO<Boolean> reduce(@RequestParam String userId, @RequestParam BigDecimal money) {
        return ResponseEntityDTO.success(accountService.reduce(userId, money));
    }
}