package org.chy.carorder.service.impl;

import org.chy.carorder.dao.OrderLineDao;
import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.service.OrderLineServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by chy on 2022/3/11.
 */
@Service
public class OrderLineServicesImpl implements OrderLineServices {
    @Resource
    private OrderLineDao orderLineDao;

    @Override
    public boolean add(OrderCreateReqDto entity) {
        return orderLineDao.add(entity);
    }
}