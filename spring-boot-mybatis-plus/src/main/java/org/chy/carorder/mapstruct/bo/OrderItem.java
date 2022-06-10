package org.chy.carorder.mapstruct.bo;

/** 
* @Title: OrderItem
* @Description:
* @author chy
* @date 2022/6/6 19:47
*/
public class OrderItem {

    private String name;
    private Long quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
