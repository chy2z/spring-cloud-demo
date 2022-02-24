package org.chy.carorder.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * tk.mybatis
 *
 * @author admin
 * Created by chy on 2021/8/11.
 */
@TableName("car_order")
public class CarOrderEntity implements Serializable {
    private static final long serialVersionUID = -5007155473903409598L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "carNo")
    private String carNo;

    @TableField(value = "orderNo")
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