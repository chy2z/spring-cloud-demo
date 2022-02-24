package org.chy.carorder.dto.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by chy on 2022/1/23.
 */
public class CarOderSearchReqDto {
    @NotNull
    @Max(value = 65535,message = "超出最大值")
    private Integer pageNum;

    @NotNull
    @Max(value = 200,message = "超出最大值")
    private Integer pageSize;

    private String orderNo;

    private String carNo;

    private Long id;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}