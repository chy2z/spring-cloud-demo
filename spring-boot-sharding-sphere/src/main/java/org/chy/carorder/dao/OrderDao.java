package org.chy.carorder.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.entity.OrderEntity;

/**
 *
 * @author chy
 * @date 2022/3/10
 */
public interface OrderDao extends IService<OrderEntity> {
    /**
     * 新增订单
     * @param entity
     * @return
     */
    boolean add(OrderCreateReqDto entity);
}