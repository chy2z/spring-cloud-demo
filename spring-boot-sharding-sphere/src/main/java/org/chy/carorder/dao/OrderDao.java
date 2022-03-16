package org.chy.carorder.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.dto.req.OrderSearchReqDTO;
import org.chy.carorder.dto.resp.OrderSearchRespDTO;
import org.chy.carorder.entity.OrderEntity;

/**
 *
 * @author chy
 * @date 2022/3/10
 */
public interface OrderDao extends IService<OrderEntity> {
    /**
     * 新增订单
     * @param reqDto
     * @return
     */
    boolean add(OrderCreateReqDto reqDto);

    /**
     * 查询订单信息
     * @param reqDTO
     * @return
     */
    OrderSearchRespDTO search(OrderSearchReqDTO reqDTO);
}