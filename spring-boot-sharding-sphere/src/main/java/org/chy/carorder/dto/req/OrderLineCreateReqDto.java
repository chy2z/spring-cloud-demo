package org.chy.carorder.dto.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by chy on 2022/3/11.
 */
public class OrderLineCreateReqDto {
    @ApiModelProperty(value = "id",hidden = true)
    private Long id;

    @ApiModelProperty(value = "订单id",hidden = true)
    private Long orderId;

    @ApiModelProperty(value = "商品名称")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "价格")
    @NotNull
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}