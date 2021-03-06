package org.chy.carorder.service;

import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.dto.req.OrderSearchReqDTO;
import org.chy.carorder.dto.resp.OrderSearchRespDTO;

/**
 *
 * @author chy
 * @date 2022/3/10
 */
public interface OrderServices {
    boolean add(OrderCreateReqDto entity);
    OrderSearchRespDTO search(OrderSearchReqDTO reqDTO);
}