package org.chy.carorder.bo;

/**
 * @author chy
 * @Title: 订单搜索
 * @Description:
 * @date 2023/4/5 22:43
 */
public class CarOderSearchBo {

    private Long id;


    private String carNo;


    private String orderNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
