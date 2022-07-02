package org.chy.carorder.service;

import org.chy.carorder.constant.SubmitTypeEnum;
import org.chy.carorder.dto.req.CarOrderSubmitReqDto;

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
     Long submit(CarOrderSubmitReqDto reqDto);

     /**
      * 获取提交类型
      * @return
      */
     SubmitTypeEnum getType();
}