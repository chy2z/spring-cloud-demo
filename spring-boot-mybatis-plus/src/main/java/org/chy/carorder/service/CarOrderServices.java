package org.chy.carorder.service;

import org.chy.carorder.config.RedissonCacheConfig;
import org.chy.carorder.dto.req.CarOderSearchReqDto;
import org.chy.carorder.dto.req.CarOrderAddReqDto;
import org.chy.carorder.dto.req.CarOrderSubmitReqDto;
import org.chy.carorder.dto.resp.CarOderSearchRespDto;
import org.chy.carorder.entity.CarOrderEntity;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface CarOrderServices {

    CarOrderEntity selectByPrimaryKey(Integer id);

    List<CarOrderEntity> selectAll();

    List<CarOrderEntity> selectPage(Long pageNum, Long limit);

    @Cacheable(cacheNames = RedissonCacheConfig.CACHE_CAR_ORDER_INFO, key = "#carNo")
    List<CarOrderEntity> searchByCarNo(String carNo);

    CarOderSearchRespDto search(CarOderSearchReqDto reqDto);

    boolean add(CarOrderAddReqDto reqDto);

    Integer submit(CarOrderSubmitReqDto reqDto);
}
