package org.chy.carorder.mapstruct.mapper;

import org.chy.carorder.mapstruct.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/6/6 17:47
 */
@Mapper(mappingControl = DeepClone.class)
public interface MapStructCloner {
    MapStructCloner MAPPER = Mappers.getMapper(MapStructCloner.class);

    /**
     * 深度克隆对象
     * @param customerDto
     * @return
     */
    CustomerDto clone(CustomerDto customerDto);
}