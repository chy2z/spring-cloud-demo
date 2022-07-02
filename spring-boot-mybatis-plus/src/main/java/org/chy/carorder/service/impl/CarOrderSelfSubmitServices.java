package org.chy.carorder.service.impl;

import org.chy.carorder.constant.SubmitTypeEnum;
import org.chy.carorder.dto.req.CarOrderSubmitReqDto;
import org.chy.carorder.service.CarOrderSubmitServices;
import org.springframework.stereotype.Service;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/7/2 22:49
 */
@Service
public class CarOrderSelfSubmitServices implements CarOrderSubmitServices {

    @Override
    public Long submit(CarOrderSubmitReqDto reqDto) {
        return 1001L;
    }

    @Override
    public SubmitTypeEnum getType() {
        return SubmitTypeEnum.SELF;
    }
}
