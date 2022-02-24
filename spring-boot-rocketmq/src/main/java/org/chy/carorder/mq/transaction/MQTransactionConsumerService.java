package org.chy.carorder.mq.transaction;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.chy.carorder.mq.MQConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by chy on 2022/2/20.
 */
@Component
public class MQTransactionConsumerService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MQConsumerService.class);

    @Service
    @RocketMQMessageListener(topic = "${my.rocketmq.txTopic}", selectorExpression = "*", consumerGroup = "Chy_Tx_Group")
    public class TxConsumer implements RocketMQListener<String> {
        @Override
        public void onMessage(String message) {
            LOGGER.info("监听到事物消息：message={}", message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "${my.rocketmq.txTopic}", selectorExpression = "*", consumerGroup = "Chy_Tx_Group_Two")
    public class TxConsumer2 implements RocketMQListener<MessageExt> {
        @Override
        public void onMessage(MessageExt messageExt) {
            byte[] body = messageExt.getBody();
            String message = new String(body);
            String transId = messageExt.getProperties().get(RocketMQHeaders.TRANSACTION_ID);
            LOGGER.info("监听到事物消息：messageExt = {},transId = {}", message, transId);
        }
    }
}