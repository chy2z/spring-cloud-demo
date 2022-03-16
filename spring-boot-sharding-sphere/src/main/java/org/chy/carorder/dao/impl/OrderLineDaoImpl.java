package org.chy.carorder.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chy.carorder.dao.OrderLineDao;
import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.dto.req.OrderLineCreateReqDto;
import org.chy.carorder.entity.OrderLineEntity;
import org.chy.carorder.mapper.ext.OrderLineMapperExt;
import org.chy.carorder.util.IdWorkUitl;
import org.springframework.stereotype.Service;

/**
 *
 * @author chy
 * @date 2022/3/11
 */
@Service
public class OrderLineDaoImpl extends ServiceImpl<OrderLineMapperExt, OrderLineEntity> implements OrderLineDao {
    @Override
    public boolean add(OrderCreateReqDto reqDto) {
        for (OrderLineCreateReqDto lineCreateReqDto : reqDto.getLines()) {
            OrderLineEntity orderLineEntity = new OrderLineEntity();
            orderLineEntity.setId(IdWorkUitl.next());
            orderLineEntity.setOrderId(reqDto.getId());
            orderLineEntity.setName(lineCreateReqDto.getName());
            orderLineEntity.setPrice(lineCreateReqDto.getPrice());
            getBaseMapper().insert(orderLineEntity);
        }
        return true;
    }
}