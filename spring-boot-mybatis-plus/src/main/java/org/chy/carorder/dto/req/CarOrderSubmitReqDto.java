package org.chy.carorder.dto.req;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/7/2 22:36
 */
public class CarOrderSubmitReqDto {
    private Integer submitType;
    private String carNo;
    private String orderNo;

    public Integer getSubmitType() {
        return submitType;
    }

    public void setSubmitType(Integer submitType) {
        this.submitType = submitType;
    }

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