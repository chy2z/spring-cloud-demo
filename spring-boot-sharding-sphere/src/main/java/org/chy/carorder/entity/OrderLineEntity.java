package org.chy.carorder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by chy on 2022/3/10.
 */
@TableName("order_line")
public class OrderLineEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3815878187350167456L;
    /**
     * 设置 IdType.AUTO 会自动生成 分布式id
     * 设置 IdType.INPUT 需要自己生成分布式id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private Long orderId;

    private String name;

    private Integer num;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
