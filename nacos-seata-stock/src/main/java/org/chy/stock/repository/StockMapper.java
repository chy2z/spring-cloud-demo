package org.chy.stock.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.chy.stock.model.StockEntity;

/**
 * @author chy
 * @Title: 库存Mapper
 * @Description:
 * @date 2022/12/2 22:36
 */
public interface StockMapper extends BaseMapper<StockEntity> {
    StockEntity selectByCommodityCode(@Param("commodityCode") String commodityCode);

    int reduce(StockEntity record);
}