package com.ozygod.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: 实体类基类
 * @description:
 * @author: Joey
 * @date: Created in 15:36 2018/8/30 0030.
 */
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 670281851077250289L;

    /**租户id*/
    protected String tenantId;
    /**最后更新人用户id*/
    protected String lastUpdatedUserId;
    /**最后更新时间*/
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date lastUpdatedStamp;
    /**创建人用户id*/
    protected String createdUserId;
    /**创建时间*/
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date createdStamp;
}
