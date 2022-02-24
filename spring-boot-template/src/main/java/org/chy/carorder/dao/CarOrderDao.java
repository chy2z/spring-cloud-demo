package org.chy.carorder.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chy.carorder.dto.req.CarOderSearchReqDto;
import org.chy.carorder.dto.resp.CarOderSearchRespDto;
import org.chy.carorder.entity.CarOrderEntity;

import java.util.List;

/**
 * Created by chy on 2022/1/23.
 */
public interface CarOrderDao extends IService<CarOrderEntity> {
    /**
     * 原生分页
     * @param page
     * @param limit
     * @return
     */
    List<CarOrderEntity> selectPage(Long page , Long limit);

    /**
     * 原生分页(xml)
     * @param reqDtoo
     * @return
     */
    CarOderSearchRespDto search(CarOderSearchReqDto reqDtoo);
}

