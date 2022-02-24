package org.chy.carorder.mq.transaction;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 发送事物消息
 *
 * Created by chy on 2022/2/20.
 */
@Component
public class MQTransactionProducerService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MQTransactionProducerService.class);

    @Value("${my.rocketmq.txTopic}")
    private String txTopic;

    @Value("${my.rocketmq.txProducerGroup}")
    private String txProducerGroup;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送事物消息
     *
     * @param msg
     */
    public SendResult sendMessageInTransaction(String msg) {
        String transId=String.valueOf(System.currentTimeMillis());
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transId)
                .build();
        // myTransactionGroup要和@RocketMQTransactionListener(txProducerGroup = "myTransactionGroup")定义的一致
        SendResult result = this.rocketMQTemplate.sendMessageInTransaction(txProducerGroup, txTopic, message, null);
        LOGGER.info("【sendMessageInTransaction】transId= {} , sendResult = {}", transId,JSON.toJSONString(result));
        return result;
    }
}
