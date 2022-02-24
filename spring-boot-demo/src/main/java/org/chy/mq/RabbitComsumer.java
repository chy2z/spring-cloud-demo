package org.chy.mq;

import com.rabbitmq.client.Channel;
import org.chy.carorder.config.RabbitMQConfig;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Rabbit  消息消费
 * Created by chy on 2021/8/12.
 */
@Component
public class RabbitComsumer {
    /*
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConfig.ITEM_QUEUE, durable = "true"),
            exchange = @Exchange(name = RabbitMQConfig.ITEM_DIRECT_EXCHANGE,
                    durable = "true",
                    type = ExchangeTypes.DIRECT,
                    ignoreDeclarationExceptions = "FALSE"),
                     key = RabbitMQConfig.ITEM_DIRECT_ROUTING
    ),concurrency = "2")
    public void onMessage(Message message, Channel channel) throws Exception {
        //	1. 收到消息以后进行业务端消费处理
        System.err.println("-----------------------");
        System.err.println("消费消息:" + message.getPayload());
        System.err.println("消息head:" + message.getHeaders().get("x-head"));
        System.err.println("-----------------------");
        //  2. 处理成功之后 获取deliveryTag 并进行手工的ACK操作, 因为我们配置文件里配置的是 手工签收
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }
     */


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConfig.ITEM_QUEUE_DLX, durable = "true"),
            exchange = @Exchange(name = RabbitMQConfig.ITEM_DIRECT_EXCHANGE_DLX,
                    durable = "true",
                    type = ExchangeTypes.DIRECT,
                    ignoreDeclarationExceptions = "FALSE"),
            key = RabbitMQConfig.ITEM_DIRECT_ROUTING_DLX
    ),concurrency = "2")
    public void onDlxMessage(Message message, Channel channel) throws Exception {
        //	1. 收到消息以后进行业务端消费处理
        System.err.println("-----------------------");
        System.err.println("死信消费消息:" + message.getPayload());
        System.err.println("死信消息head:" + message.getHeaders().get("x-head"));
        System.err.println("-----------------------");
        //  2. 处理成功之后 获取deliveryTag 并进行手工的ACK操作, 因为我们配置文件里配置的是 手工签收
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

}