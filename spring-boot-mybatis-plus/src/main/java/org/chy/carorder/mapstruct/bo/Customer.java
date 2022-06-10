/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.chy.carorder.mapstruct.bo;

import org.chy.carorder.mapstruct.dto.OrderItemDto;
import org.chy.carorder.mapstruct.dto.OrderItemKeyDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/** 
* @Title: Customer
* @Description:
* @author chy
* @date 2022/6/6 19:57
*/
public class Customer {
    private Long id;
    private Integer age;
    private String name;
    private List<OrderItem> orderItems;
    private Map<OrderItemKeyDto, OrderItemDto> stock;
    private boolean man;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Map<OrderItemKeyDto, OrderItemDto> getStock() {
        return stock;
    }

    public void setStock(Map<OrderItemKeyDto, OrderItemDto> stock) {
        this.stock = stock;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean getMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }
}