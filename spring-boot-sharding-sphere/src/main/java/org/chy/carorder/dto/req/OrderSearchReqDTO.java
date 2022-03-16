package org.chy.carorder.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@ApiModel(value = "订单列表查询入参")
public class OrderSearchReqDTO {

    @ApiModelProperty(value = "当前页", required = true)
    @NotNull
    @Max(value = 65535,message = "超出最大值")
    private Integer pageNum;

    @ApiModelProperty(value = "每页条数", required = true)
    @NotNull
    @Max(value = 200,message = "超出最大值")
    private Integer pageSize;

    @ApiModelProperty(value = "商品名称")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}