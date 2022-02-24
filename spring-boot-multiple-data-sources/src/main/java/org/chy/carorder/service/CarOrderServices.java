package org.chy.carorder.service;

import org.chy.carorder.entity.CarOrder;

import java.util.List;

/**
 * tk.mybatis
 *
 * Created by chy on 2021/8/11.
 */
public interface CarOrderServices {
    CarOrder selectByPrimaryKey(Integer id);

    List<CarOrder> selectAll();
}
