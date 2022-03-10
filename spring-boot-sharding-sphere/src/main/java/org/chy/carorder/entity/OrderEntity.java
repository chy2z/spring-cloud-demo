package org.chy.carorder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * Created by chy on 2022/3/10.
 */
@TableName("order")
public class OrderEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8707963495851307735L;

    /**
     * 设置 IdType.AUTO 会自动生成 分布式id
     * 设置 IdType.INPUT 需要自己生成分布式id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private Integer userId;

    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}