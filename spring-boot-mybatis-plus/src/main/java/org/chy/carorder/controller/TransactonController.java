package org.chy.carorder.controller;

import org.chy.carorder.dto.req.CarOrderSubmitReqDto;
import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.chy.carorder.service.CarOrderServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/7/3 9:44
 */
@RestController
@RequestMapping("/transaction/car/order/")
public class TransactonController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TransactonController.class);

    @Autowired
    private CarOrderServices carOrderServices;


    /**
     * 测试提交订单
     * http://localhost:8080/transaction/car/order/submit
     *
     * @return
     */
    @PostMapping("submit")
    public ResponseEntityDTO<Integer> submit(@RequestBody CarOrderSubmitReqDto reqDto) {
        Integer id = carOrderServices.submit(reqDto);
        return ResponseEntityDTO.success(id);
    }

}