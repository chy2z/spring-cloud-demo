package org.chy.carorder.dto.resp;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chy on 2022/3/10.
 */
public class OrderCreateRespDto {
    @ApiModelProperty(value = "订单编号")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}