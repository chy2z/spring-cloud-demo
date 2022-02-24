package org.chy.carorder.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import org.chy.carorder.entity.CarOrderEntity;

import java.util.List;

/**
 * Created by chy on 2022/1/23.
 */
public class CarOderSearchRespDto {
    @ApiModelProperty(value = "总记录数")
    private Long total;

    @ApiModelProperty(value = "总页数")
    private Long pages;

    @ApiModelProperty(value = "当前页")
    private Long pageNum;

    @ApiModelProperty(value = "每页条数")
    private Long pageSize;

    @ApiModelProperty(value = "返回内容")
    private List<CarOrderEntity> list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public List<CarOrderEntity> getList() {
        return list;
    }

    public void setList(List<CarOrderEntity> list) {
        this.list = list;
    }
}
