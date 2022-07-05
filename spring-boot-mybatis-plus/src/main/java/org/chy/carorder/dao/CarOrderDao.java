package org.chy.carorder.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chy.carorder.config.RedissonCacheConfig;
import org.chy.carorder.dto.req.CarOderSearchReqDto;
import org.chy.carorder.dto.resp.CarOderSearchRespDto;
import org.chy.carorder.entity.CarOrderEntity;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

/**
 * Created by chy on 2022/1/23.
 */
public interface CarOrderDao extends IService<CarOrderEntity> {
    /**
     * 原生分页
     *
     * @param page
     * @param limit
     * @return
     */
    List<CarOrderEntity> selectPage(Long page, Long limit);

    /**
     * 原生分页(xml)
     *
     * @param reqDtoo
     * @return
     */
    CarOderSearchRespDto search(CarOderSearchReqDto reqDtoo);

    /**
     * 根据车牌查询记录
     * @param carNo
     * @return
     */
    List<CarOrderEntity> searchByCarNo(String carNo);

    /**
     * 增加
     *
     * 增加记录会删除缓存
     *
     * @param entity
     * @return
     */
    @CacheEvict(cacheNames = RedissonCacheConfig.CACHE_CAR_ORDER_INFO, key = "#entity.carNo")
    boolean add(CarOrderEntity entity);

    /**
     * 更新库存数量
     * @param id
     * @param stockCount
     * @return
     */
    boolean updateStockById(Integer id, Integer stockCount);

    /**
     * 更新库存数量
     * @param id
     * @param stockCount
     * @return
     */
    boolean updateStockByCarNo(String carNo, Integer stockCount);

    /**
     * 删除
     *
     * 删除记录也会删除缓存
     *
     * @param carNo
     * @return
     */
    @CacheEvict(cacheNames = RedissonCacheConfig.CACHE_CAR_ORDER_INFO, key = "#carNo")
    boolean delete(String carNo);
}