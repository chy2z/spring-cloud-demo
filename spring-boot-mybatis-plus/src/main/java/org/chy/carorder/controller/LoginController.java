package org.chy.carorder.controller;

import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.chy.guard.annotation.AccessGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chy on 2022/2/14.
 */
@RestController
@RequestMapping("/login/")
public class LoginController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CarOrderController.class);

    /**
     * 测试获取配置
     * http://localhost:8080/login/system
     *
     * @return
     */
    @AccessGuard
    @PostMapping("system")
    public ResponseEntityDTO<Boolean> login() {
        return ResponseEntityDTO.success(true);
    }
}