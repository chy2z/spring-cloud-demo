package org.chy.carorder.service.impl;

import org.chy.carorder.dao.OrderDao;
import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.dto.req.OrderSearchReqDTO;
import org.chy.carorder.dto.resp.OrderSearchRespDTO;
import org.chy.carorder.service.OrderServices;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by chy on 2022/3/10.
 */
@Service
public class OrderServicesImpl implements OrderServices {
    @Resource
    private OrderDao orderDao;

    @Override
    public boolean add(OrderCreateReqDto reqDto) {
        return orderDao.add(reqDto);
    }

    @Override
    public OrderSearchRespDTO search(OrderSearchReqDTO reqDTO) {
        return orderDao.search(reqDTO);
    }
}