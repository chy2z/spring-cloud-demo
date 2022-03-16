package org.chy.carorder.service;

import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.dto.req.OrderSearchReqDTO;
import org.chy.carorder.dto.resp.OrderCreateRespDto;
import org.chy.carorder.dto.resp.OrderSearchRespDTO;

/**
 * 订单领域服务
 */
public interface OrderAggregationServices {
    OrderCreateRespDto submit(OrderCreateReqDto reqDto);

    OrderSearchRespDTO search(OrderSearchReqDTO reqDTO);
}
