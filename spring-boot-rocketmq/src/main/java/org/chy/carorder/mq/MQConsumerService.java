package org.chy.carorder.mq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.chy.carorder.entity.CarOrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by chy on 2022/2/19.
 */
@Component
public class MQConsumerService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MQConsumerService.class);

    /**
     * topic需要和生产者的topic一致，consumerGroup属性是必须指定的，内容可以随意
     * selectorExpression的意思指的就是tag，默认为“*”，不设置的话会监听所有消息
     */
    @Service
    @RocketMQMessageListener(topic = "${my.rocketmq.topic}", selectorExpression = "tag-event-one", consumerGroup = "Chy_Group_One")
    public class ConsumerSend implements RocketMQListener<CarOrderEntity> {
        // 监听到消息就会执行此方法
        @Override
        public void onMessage(CarOrderEntity car) {
            LOGGER.info("监听到消息：car={}", JSON.toJSONString(car));
        }
    }

    /**
     * 能收到  topic 所有消息
     */
    @Service
    @RocketMQMessageListener(topic = "${my.rocketmq.topic}", consumerGroup = "Chy_Group_Two")
    public class ConsumerSend2 implements RocketMQListener<String> {
        @Override
        public void onMessage(String message) {
            LOGGER.info("监听到消息：message={}", message);
        }
    }

    /**
     * MessageExt：是一个消息接收通配符，不管发送的是String还是对象，都可接收，当然也可以像上面明确指定类型（我建议还是指定类型较方便）
     */
    @Service
    @RocketMQMessageListener(topic = "${my.rocketmq.topic}", selectorExpression = "tag-event-two", consumerGroup = "Chy_Group_Three")
    public class ConsumerSend3 implements RocketMQListener<MessageExt> {
        @Override
        public void onMessage(MessageExt messageExt) {
            byte[] body = messageExt.getBody();
            String msg = new String(body);
            LOGGER.info("监听到消息：messageExt={},properties={}", msg, JSON.toJSONString(messageExt.getProperties()));
        }
    }
}