package org.chy.carorder.service;

import org.chy.carorder.dto.req.OrderCreateReqDto;

/**
 * Created by chy on 2022/3/11.
 */
public interface OrderLineServices {
    boolean add(OrderCreateReqDto entity);
}
