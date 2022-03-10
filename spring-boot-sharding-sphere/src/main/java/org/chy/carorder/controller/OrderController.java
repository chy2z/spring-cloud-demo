package org.chy.carorder.controller;

import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.dto.resp.OrderCreateRespDto;
import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.chy.carorder.service.OrderServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by chy on 2022/3/10.
 */
@RestController
@RequestMapping("/order/")
public class OrderController {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderServices orderServices;

    /**
     * 测试获取配置
     * http://localhost:8080/order/submit
     *
     * @return
     */
    @PostMapping("submit")
    public ResponseEntityDTO<OrderCreateRespDto> submit(@RequestBody OrderCreateReqDto reqDto) {
        OrderCreateRespDto respDto = new OrderCreateRespDto();
        reqDto.setId(System.currentTimeMillis());
        LOGGER.info("default id:{}", reqDto.getId());
        orderServices.add(reqDto);
        respDto.setId(reqDto.getId());
        return ResponseEntityDTO.success(respDto);
    }
}