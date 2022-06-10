package org.chy.carorder.mapstruct.mapper;

import org.chy.carorder.mapstruct.bo.Customer;
import org.chy.carorder.mapstruct.dto.CustomerDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/6/6 19:47
 */
/*@Mapper(uses={OrderItemMapper.class})*/
@Mapper
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(source = "customerName", target = "name"),
            @Mapping(source = "orders", target = "orderItems")
    })
    Customer toCustomer(CustomerDto customerDto);

    @InheritInverseConfiguration
    CustomerDto fromCustomer(Customer customer);
}