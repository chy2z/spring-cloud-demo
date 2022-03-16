package org.chy.carorder.dto.req;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by chy on 2022/3/10.
 */
public class OrderCreateReqDto {
    @ApiModelProperty(value = "id",hidden = true)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @NotNull
    private Integer userId;

    @ApiModelProperty(value = "送货地址")
    @NotBlank
    private String address;

    @ApiModelProperty(value = "商品信息")
    @NotNull
    private List<OrderLineCreateReqDto> lines;

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

    public List<OrderLineCreateReqDto> getLines() {
        return lines;
    }

    public void setLines(List<OrderLineCreateReqDto> lines) {
        this.lines = lines;
    }
}