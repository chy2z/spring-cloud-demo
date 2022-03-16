package org.chy.carorder.service.impl;

import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.dto.req.OrderSearchReqDTO;
import org.chy.carorder.dto.resp.OrderCreateRespDto;
import org.chy.carorder.dto.resp.OrderSearchRespDTO;
import org.chy.carorder.service.OrderAggregationServices;
import org.chy.carorder.service.OrderLineServices;
import org.chy.carorder.service.OrderServices;
import org.chy.carorder.util.IdWorkUitl;
import org.chy.carorder.util.TransactionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author admin
 */
@Service
public class OrderAggregationServicesImpl implements OrderAggregationServices {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderAggregationServicesImpl.class);

    @Resource
    private OrderServices orderServices;

    @Resource
    private OrderLineServices orderLineServices;

    @Resource
    private TransactionHelper transactionHelper;

    @Override
    public OrderCreateRespDto submit(OrderCreateReqDto reqDto) {
        OrderCreateRespDto respDto = new OrderCreateRespDto();
        reqDto.setId(IdWorkUitl.next());
        LOGGER.info("default id:{}", reqDto.getId());
        // 开启事物
        transactionHelper.withTransaction(() -> {
            orderServices.add(reqDto);
            orderLineServices.add(reqDto);
            return Boolean.TRUE;
        });
        respDto.setId(reqDto.getId());
        return respDto;
    }

    @Override
    public OrderSearchRespDTO search(OrderSearchReqDTO reqDTO) {
        return orderServices.search(reqDTO);
    }
}