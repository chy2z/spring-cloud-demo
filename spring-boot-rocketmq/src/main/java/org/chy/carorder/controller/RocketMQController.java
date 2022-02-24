package org.chy.carorder.controller;

import org.apache.rocketmq.client.producer.SendResult;
import org.chy.carorder.entity.CarOrderEntity;
import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.chy.carorder.mq.MQProducerService;
import org.chy.carorder.mq.transaction.MQTransactionProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chy on 2022/2/19.
 */
@RestController
@RequestMapping("/rocketmq/")
public class RocketMQController {
    @Autowired
    private MQProducerService mqProducerService;

    @Autowired
    private MQTransactionProducerService mqTransactionProducerService;

    @GetMapping("/send")
    public void send() {
        CarOrderEntity carOrderEntity = new CarOrderEntity();
        carOrderEntity.setId(1111111111111L);
        carOrderEntity.setOrderNo("1");
        carOrderEntity.setCarNo("皖E12345");
        mqProducerService.send(carOrderEntity);
    }

    @GetMapping("/sendAsyncMsg")
    public ResponseEntityDTO<Boolean> sendAsyncMsg() {
        mqProducerService.sendAsyncMsg("我的异步消息");
        return ResponseEntityDTO.success(true);
    }

    @GetMapping("/sendTagMsg")
    public ResponseEntityDTO<SendResult> sendTagMsg() {
        SendResult sendResult = mqProducerService.sendTagMsg("我的消息");
        return ResponseEntityDTO.success(sendResult);
    }

    @GetMapping("/sendTagEntity")
    public ResponseEntityDTO<SendResult> sendTagEntity() {
        CarOrderEntity carOrderEntity = new CarOrderEntity();
        carOrderEntity.setId(22222222222L);
        carOrderEntity.setOrderNo("2");
        carOrderEntity.setCarNo("皖E12345");
        SendResult sendResult = mqProducerService.sendTagEntity(carOrderEntity);
        return ResponseEntityDTO.success(sendResult);
    }

    @GetMapping("/sendDelayMsg")
    public ResponseEntityDTO<Boolean> sendDelayMsg() {
        mqProducerService.sendDelayMsg("我的延迟消息", 3);
        return ResponseEntityDTO.success(true);
    }

    @GetMapping("/sendInTransactionMsg")
    public ResponseEntityDTO<SendResult> sendInTransactionMsg() {
        SendResult sendResult = mqTransactionProducerService.sendMessageInTransaction("我的异步消息");
        return ResponseEntityDTO.success(sendResult);
    }
}
