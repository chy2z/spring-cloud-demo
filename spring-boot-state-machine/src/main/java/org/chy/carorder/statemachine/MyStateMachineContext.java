package org.chy.carorder.statemachine;

import org.chy.carorder.bo.CarOrderBo;

/**
 * @author chy
 * @Title: 订单状态机内容上下文
 * @Description:
 * @date 2023/4/5 22:40
 */
public class MyStateMachineContext {

    /**
     * 订单业务对象
     */
    private CarOrderBo carOderBo;

    public Integer del;

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public CarOrderBo getCarOderBo() {
        return carOderBo;
    }

    public void setCarOderBo(CarOrderBo carOrderBo) {
        this.carOderBo = carOrderBo;
    }
}