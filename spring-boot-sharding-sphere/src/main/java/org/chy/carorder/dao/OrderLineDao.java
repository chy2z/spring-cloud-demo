package org.chy.carorder.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.entity.OrderLineEntity;

/**
 * Created by chy on 2022/3/11.
 */
public interface OrderLineDao extends IService<OrderLineEntity> {
    /**
     * 新增订单
     * @param entity
     * @return
     */
    boolean add(OrderCreateReqDto entity);
}
