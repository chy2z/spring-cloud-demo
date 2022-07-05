package org.chy.carorder.service;

import org.chy.carorder.constant.SubmitTypeEnum;
import org.chy.carorder.dto.req.CarOrderSubmitReqDto;
import org.chy.carorder.entity.CarOrderEntity;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/7/2 22:35
 */
public interface CarOrderSubmitServices {
     /**
      * 提交订单
      * @param reqDto
      * @return
      */
     Integer submit(CarOrderSubmitReqDto reqDto);

     /**
      * 更新库存
      * @param id
      * @param stock
      */
     void updateStock(CarOrderEntity carOrderEntity, Integer stock);


     /**
      * 获取提交类型
      * @return
      */
     SubmitTypeEnum getType();
}