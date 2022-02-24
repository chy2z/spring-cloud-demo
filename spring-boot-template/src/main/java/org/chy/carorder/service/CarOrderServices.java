package org.chy.carorder.service;

import org.chy.carorder.dto.req.CarOderSearchReqDto;
import org.chy.carorder.dto.resp.CarOderSearchRespDto;
import org.chy.carorder.entity.CarOrderEntity;

import java.util.List;


public interface CarOrderServices {

    CarOrderEntity selectByPrimaryKey(Integer id);

    List<CarOrderEntity> selectAll();

    List<CarOrderEntity> selectPage(Long pageNum, Long limit);

    CarOderSearchRespDto search(CarOderSearchReqDto reqDto);
}
