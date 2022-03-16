package org.chy.carorder.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.chy.carorder.dao.OrderDao;
import org.chy.carorder.dto.req.OrderCreateReqDto;
import org.chy.carorder.dto.req.OrderSearchReqDTO;
import org.chy.carorder.dto.resp.OrderSearchRespDTO;
import org.chy.carorder.entity.OrderEntity;
import org.chy.carorder.mapper.ext.OrderMapperExt;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 订单
 *
 * @author chy
 * @date 2022/3/10
 */
@Service
public class OrderDaoImpl extends ServiceImpl<OrderMapperExt, OrderEntity> implements OrderDao {

    @Override
    public boolean add(OrderCreateReqDto reqDto) {
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setId(reqDto.getId());
        orderEntity.setAddress(reqDto.getAddress());
        orderEntity.setUserId(reqDto.getUserId());
        return getBaseMapper().insert(orderEntity) > 0;
    }

    @Override
    public OrderSearchRespDTO search(OrderSearchReqDTO reqDTO) {
        OrderSearchRespDTO respDTO = new OrderSearchRespDTO();
        Page<String> orderPage = new Page<>(reqDTO.getPageNum(), reqDTO.getPageSize(), true);
        List<Long> orderIds = super.getBaseMapper().search(orderPage, reqDTO);
        respDTO.setPages(orderPage.getPages());
        respDTO.setPageNum(orderPage.getCurrent());
        respDTO.setTotal(orderPage.getTotal());
        respDTO.setPageSize(orderPage.getSize());
        if (CollectionUtils.isEmpty(orderIds)) {
            respDTO.setList(Collections.EMPTY_LIST);
            return respDTO;
        }
        // 获取订单主信息
        LambdaQueryWrapper<OrderEntity> orderPOWrapper = new LambdaQueryWrapper();
        orderPOWrapper.in(OrderEntity::getId, orderIds);
        List<OrderEntity> orderEntityList = super.getBaseMapper().selectList(orderPOWrapper);
        respDTO.setList(orderEntityList);
        return respDTO;
    }
}