package org.chy.carorder.mapstruct.mapper;

import org.chy.carorder.mapstruct.bo.OrderItem;
import org.chy.carorder.mapstruct.dto.OrderItemDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/6/6 19:50
 */
@Mapper
public interface OrderItemMapper {
    OrderItemMapper MAPPER = Mappers.getMapper(OrderItemMapper.class);

    OrderItem toOrderItem(OrderItemDto orderItemDto);

    @InheritInverseConfiguration
    OrderItemDto fromOrderItem(OrderItem orderItem);
}