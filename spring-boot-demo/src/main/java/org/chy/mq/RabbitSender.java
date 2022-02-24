package org.chy.mq;

import org.chy.carorder.config.RabbitMQConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Rabbit 消息发送
 * Created by chy on 2021/8/12.
 */
@Component
public class RabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 	对外发送消息的方法
     * @param message 	具体的消息内容
     * @param properties	额外的附加属性
     * @throws Exception
     */
    public void send(Object message, Map<String, Object> properties)  {
        MessageHeaders mhs = new MessageHeaders(properties);
        Message<?> msg = MessageBuilder.createMessage(message, mhs);
        MessagePostProcessor mpp = new MessagePostProcessor() {
            @Override
            public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message)
                    throws AmqpException {
                System.err.println("---> post to do: " + message);
                return message;
            }
        };
        rabbitTemplate.convertAndSend(RabbitMQConfig.ITEM_DIRECT_EXCHANGE, RabbitMQConfig.ITEM_DIRECT_ROUTING, msg, mpp);
    }
}