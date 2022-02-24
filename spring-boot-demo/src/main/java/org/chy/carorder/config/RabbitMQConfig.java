package org.chy.carorder.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * dmq配置
 * Created by chy on 2021/8/12.
 */
@Configuration
public class RabbitMQConfig {
    //交换机名称
    public static final String ITEM_DIRECT_EXCHANGE = "item_direct_exchange";

    //交换机路由
    public static final String ITEM_DIRECT_ROUTING = "item_direct_routing";

    //队列名称
    public static final String ITEM_QUEUE = "item_queue";


    public static final String ITEM_DIRECT_EXCHANGE_DLX = "item_direct_exchange_dlx";

    public static final String ITEM_DIRECT_ROUTING_DLX = "item_direct_routing_dlx";

    public static final String ITEM_QUEUE_DLX = "item_queue_dlx";

    /**
     * 声明交换机
     * @return
     */
    @Bean("itemDirectExchange")
    public Exchange itemDirectExchange(){
        return ExchangeBuilder.directExchange(ITEM_DIRECT_EXCHANGE).durable(true).build();
    }

    /**
     * 声明队列
     * @return
     */
    @Bean("itemDirectQueue")
    public Queue itemDirectQueue(){
        Map<String, Object> args = new HashMap<>(3);
        //声明死信交换器
        args.put("x-dead-letter-exchange", ITEM_DIRECT_EXCHANGE_DLX);
        //声明死信路由键
        args.put("x-dead-letter-routing-key",ITEM_DIRECT_ROUTING_DLX);
        //声明队列消息过期时间 1分钟
        args.put("x-message-ttl",60000);
        //队列持久
        return new Queue(ITEM_QUEUE, true, false, false, args);
    }

    /**
     * 绑定队列和交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding itemQueueExchange(@Qualifier("itemDirectQueue") Queue queue,
                                     @Qualifier("itemDirectExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ITEM_DIRECT_ROUTING).noargs();
    }

    /**
     * 声明死信交换机
     * @return
     */
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(ITEM_DIRECT_EXCHANGE_DLX);
    }

    /**
     * 声明死信队列
     * @return
     */
    @Bean
    public Queue dlxQueue() {
        return new Queue(ITEM_QUEUE_DLX, true);
    }

    /**
     * 绑定死信队列和死信交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding dlxItemQueueExchange(@Qualifier("dlxQueue") Queue queue,
                                     @Qualifier("dlxExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ITEM_DIRECT_ROUTING_DLX).noargs();
    }

}