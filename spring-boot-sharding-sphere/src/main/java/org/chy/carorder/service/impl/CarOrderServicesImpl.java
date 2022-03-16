package org.chy.carorder.service.impl;


import org.chy.carorder.dao.CarOrderDao;
import org.chy.carorder.dto.req.CarOderSearchReqDto;
import org.chy.carorder.dto.resp.CarOderSearchRespDto;
import org.chy.carorder.entity.CarOrderEntity;
import org.chy.carorder.service.CarOrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * Created by chy on 2021/8/11.
 */
@Service
public class CarOrderServicesImpl implements CarOrderServices {
    @Autowired
    CarOrderDao carOrderDao;

    @Override
    public CarOrderEntity selectByPrimaryKey(Integer id) {
        return carOrderDao.getById(id);
    }

    @Override
    public List<CarOrderEntity> selectAll() {
        return carOrderDao.list();
    }

    @Override
    public List<CarOrderEntity> selectPage(Long pageNum, Long limit) {
        return carOrderDao.selectPage(pageNum, limit);
    }

    @Override
    public CarOderSearchRespDto search(CarOderSearchReqDto reqDto) {
        return carOrderDao.search(reqDto);
    }
}