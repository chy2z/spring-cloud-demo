package org.chy.carorder.dto.req;

/**
 * Created by chy on 2022/2/25.
 */
public class CarOrderAddReqDto {
    private String carNo;

    private String orderNo;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}