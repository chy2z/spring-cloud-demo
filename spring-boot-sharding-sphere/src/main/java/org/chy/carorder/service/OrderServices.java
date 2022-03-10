package org.chy.carorder.service;

import org.chy.carorder.dto.req.OrderCreateReqDto;

/**
 *
 * @author chy
 * @date 2022/3/10
 */
public interface OrderServices {
    boolean add(OrderCreateReqDto entity);
}