package org.chy.stock.controller;

import org.chy.stock.config.AppConfig;
import org.chy.stock.response.ResponseEntityDTO;
import org.chy.stock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author chy
 * @Title: 库存接口
 * @Description:
 * @date 2022/12/2 22:36
 */
@RestController
@RefreshScope
public class StockController {
    private final static Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private StockService stockService;

    /**
     * http://127.0.0.1:8282/getNacosConfig
     * @return
     */
    @GetMapping(value = "/stock/getNacosConfig")
    public String getNacosConfig() {
        return appConfig.toString();
    }

    /**
     * 下单
     *
     * http://127.0.0.1:8282/stock/reduce?commodityCode=2001&count=1
     *
     * @param commodityCode 商品id
     * @param count 商品数量
     * @return 结果
     */
    @PostMapping("/stock/reduce")
    public ResponseEntityDTO<Boolean> reduce(String commodityCode, Integer count) {
        return  ResponseEntityDTO.success(stockService.reduce(commodityCode, count));
    }
}