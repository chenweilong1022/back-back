package com.ozygod.base.bo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ozygod.base.enums.ResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @title: 统一响应消息
 * @description:
 * @author: Joey
 * @date: Created in 16:12 2018/8/29 0029.
 */
@Data
@AllArgsConstructor
@ApiModel("通用返回消息实体")
public class ResponseBO implements Serializable {
    private static final long serialVersionUID = -8545457752000282375L;

    /**状态码*/
    @ApiModelProperty(value = "状态码", example = "0")
    private Integer code;
    /**消息*/
    @ApiModelProperty(value = "消息", example = "请求成功")
    private String msg;
    /**响应实体*/
    @ApiModelProperty(value = "响应实体")
    private Object data;
    /**每页显示条数*/
    @ApiModelProperty(value = "每页显示条数", example = "10")
    private Integer pageSize;
    /**页码*/
    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNo;
    /**总条数*/
    @ApiModelProperty(value = "总条数", example = "100")
    private Integer totalCount;
    /**总页数*/
    @ApiModelProperty(value = "总页数", example = "10")
    private Integer pages;

    public ResponseBO() {
        this(null);
    }

    public ResponseBO(Object data) {
        this(ResponseCode.S000.getCode(), ResponseCode.S000.getTitle(), data);
    }

    public ResponseBO(String msg) {
        this(ResponseCode.S000.getCode(), msg);
    }

    public ResponseBO(Integer code, String msg) {
        this(code, msg, null, 10, 1, 0, 0);
    }

    public ResponseBO(Integer code, String msg, Object data) {
        this(code, msg, data, 10, 1, 0, 0);
    }

    public static ResponseBO error(ResponseCode responseCode) {
        return error(responseCode.getCode(),responseCode.getTitle());
    }

    public static ResponseBO error(Integer code, String msg) {
        return new ResponseBO(code,msg);
    }

    public static ResponseBO error(String msg) {
        return error(ResponseCode.E500.getCode(),msg);
    }

    public static ResponseBO ok() {
        return new ResponseBO();
    }

    public static ResponseBO data(Object data) {
        ResponseBO ok = ok();
        ok.setData(data);
        return ok;
    }

    public static ResponseBO page(IPage page) {
        ResponseBO bo = ok();
        bo.setData(page.getRecords());
        bo.setPages((int) page.getPages());
        bo.setPageNo((int) page.getCurrent());
        bo.setTotalCount((int) page.getTotal());
        bo.setPageSize((int) page.getSize());
        return bo;
    }
}
