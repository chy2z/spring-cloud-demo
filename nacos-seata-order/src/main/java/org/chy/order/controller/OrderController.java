package org.chy.order.controller;

import org.chy.order.config.AppConfig;
import org.chy.order.response.ResponseEntityDTO;
import org.chy.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author chy
 * @Title: 订单接口
 * @Description:
 * @date 2022/12/2 22:36
 */
@RestController
@RefreshScope
public class OrderController {
    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private OrderService orderService;

    /**
     * http://127.0.0.1:8181/getNacosConfig
     * @return
     */
    @GetMapping(value = "/order/getNacosConfig")
    public String getNacosConfig() {
        return appConfig.toString();
    }

    /**
     * 下单
     *
     * http://127.0.0.1:8181/order/create?userId=1001&commodityCode=2001&count=1
     *
     * @param userId 用户id
     * @param commodityCode 商品id
     * @param count 商品数量
     * @return 结果
     */
    @PostMapping("/order/create")
    public ResponseEntityDTO<Boolean> create(String userId, String commodityCode, Integer count) {
        return  ResponseEntityDTO.success(orderService.create(userId, commodityCode, count));
    }
}