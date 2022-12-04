package org.chy.stock.service;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.chy.stock.model.StockEntity;
import org.chy.stock.repository.StockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chy
 * @Title: 订单服务
 * @Description:
 * @date 2022/12/2 22:36
 */
@Service
public class StockService {
    private final static Logger LOGGER = LoggerFactory.getLogger(StockService.class);
    private static final String ERROR_COMMODITY_CODE = "2000";
    @Autowired
    private StockMapper stockMapper;

    /**
     * 减库存
     *
     * @param commodityCode 商品编码
     * @param count 库存数量
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    public Boolean reduce(String commodityCode, int count) {
        LOGGER.info("stock-service------->中扣减库存开始,全局事务id:{}", RootContext.getXID());
        StockEntity stock = stockMapper.selectByCommodityCode(commodityCode);
        stock.setCount(stock.getCount() - count);
        if (ERROR_COMMODITY_CODE.equals(commodityCode)) {
            throw new RuntimeException("simulated exception:stock branch exception");
        }
        stockMapper.reduce(stock);
        LOGGER.info("stock-service------->中扣减库存开始,全局事务id:{}", RootContext.getXID());
        return true;
    }
}