package org.chy.carorder.service;

import org.chy.carorder.entity.CarOrder;
import org.chy.carorder.mapper.CarOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * tk.mybatis
 *
 * Created by chy on 2021/8/11.
 */
@Service
public class CarOrderServicesImpl implements CarOrderServices {
    @Autowired
    CarOrderMapper carOrderMapper;

    @Override
    public CarOrder selectByPrimaryKey(Integer id) {
        return carOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CarOrder> selectAll() {
        return carOrderMapper.selectAll();
    }
}