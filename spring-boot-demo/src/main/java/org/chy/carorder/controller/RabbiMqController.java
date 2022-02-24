package org.chy.carorder.controller;

import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.chy.carorder.entity.response.SysResponseCode;
import org.chy.mq.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbiMq 发送
 * Created by chy on 2021/8/12.
 */
@RestController
@RequestMapping("/mq")
public class RabbiMqController {
    @Autowired
    private RabbitSender rabbitSender;

    /**
     * 测试发送消息
     *
     * http://localhost:8080/mq/send?msg=
     */
    @GetMapping("/send")
    public ResponseEntityDTO<String> sendMsg(String msg){
        System.err.println("-----------------------");
        System.err.println("发送消息:" + msg);
        Map<String, Object> properties =new HashMap<>();
        properties.put("x-head","test");
        rabbitSender.send(msg,properties);
        return ResponseEntityDTO.success(SysResponseCode.SUCCEED.getMessage());
    }
}
