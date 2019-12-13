package com.ozygod.base.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/14
 */
@ApiModel("查询条件类基类")
public class BaseDto implements Serializable {
    private static final long serialVersionUID = 1480854636582580197L;

    /**
     * 每页显示条数
     */
    @ApiModelProperty(value = "每页显示条数", example = "10")
    private Integer pageSize;
    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数", example = "100")
    private Integer totalCount;
    /**
     * 页数
     */
    @ApiModelProperty(value = "页数", example = "10")
    private Integer pages;
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNo;

    public Integer getStart() {
        return (getPageNo() - 1) * getPageSize();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

    public Page getPage() {
        Page page = new Page(this.pages, this.pageNo);
        return page;
    }
}
