package org.chy.order.service;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.chy.order.feign.AccountFeignClient;
import org.chy.order.feign.StockFeignClient;
import org.chy.order.model.OrderEntity;
import org.chy.order.repository.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author chy
 * @Title: 订单服务
 * @Description:
 * @date 2022/12/2 22:36
 */
@Service
public class OrderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderMapper orderMapper;

    @Resource
    private AccountFeignClient accountFeignClient;

    @Resource
    private StockFeignClient stockFeignClient;

    /**
     * 下单：创建订单、减库存、口账户金额，涉及到三个服务
     *
     * @param userId        用户id
     * @param commodityCode 商品ID
     * @param count         数量
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(String userId, String commodityCode, Integer count) {
        LOGGER.info("order-service------->创建订单开始,全局事务id:{}", RootContext.getXID());
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);
        orderMapper.insert(order);
        stockFeignClient.reduce(commodityCode, count);
        accountFeignClient.reduce(userId, orderMoney);
        LOGGER.info("order-service------->创建订单结束,全局事务id:{}", RootContext.getXID());
        return true;
    }
}