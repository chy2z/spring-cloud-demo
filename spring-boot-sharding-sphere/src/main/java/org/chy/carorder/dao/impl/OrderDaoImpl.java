package org.chy.carorder.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chy.carorder.dao.OrderDao;
import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.entity.OrderEntity;
import org.chy.carorder.mapper.ext.OrderMapperExt;
import org.springframework.stereotype.Service;

/**
 * 订单
 *
 * @author chy
 * @date 2022/3/10
 */
@Service
public class OrderDaoImpl extends ServiceImpl<OrderMapperExt, OrderEntity> implements OrderDao {

    @Override
    public boolean add(OrderCreateReqDto reqDto) {
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setId(reqDto.getId());
        orderEntity.setAddress(reqDto.getAddress());
        orderEntity.setUserId(reqDto.getUserId());
        return getBaseMapper().insert(orderEntity) > 0;
    }
}