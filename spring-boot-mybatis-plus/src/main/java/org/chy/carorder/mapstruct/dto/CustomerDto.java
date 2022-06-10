package org.chy.carorder.mapstruct.dto;

import java.util.List;
import java.util.Map;

/**
 * @author chy
 */
public class CustomerDto {

    private Long id;
    private String age;
    private String customerName;
    private List<OrderItemDto> orders;
    private Map<OrderItemKeyDto, OrderItemDto> stock;
    private boolean man;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderItemDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItemDto> orders) {
        this.orders = orders;
    }

    public Map<OrderItemKeyDto, OrderItemDto> getStock() {
        return stock;
    }

    public void setStock(Map<OrderItemKeyDto, OrderItemDto> stock) {
        this.stock = stock;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean getMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }
}