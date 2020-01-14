package com.ozygod.model.zdmanage.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-14 14:35:49
 */
@Data
@TableName("tbl_sys_log")
@ApiModel("系统日志")
@Accessors(chain = true)
public class TblSysLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Long id;
	/**
	 * 用户id
	 */
	@ApiModelProperty(required=false,value="用户id")
	private Integer userId;
	/**
	 * 用户名
	 */
	@ApiModelProperty(required=false,value="用户名")
	private String username;
	/**
	 * 请求url
	 */
	@ApiModelProperty(required=false,value="请求url")
	private String hostUrl;
	/**
	 * 用户操作
	 */
	@ApiModelProperty(required=false,value="用户操作")
	private String operation;
	/**
	 * 请求参数
	 */
	@ApiModelProperty(required=false,value="请求参数")
	private String params;
	/**
	 * http请求方法
	 */
	@ApiModelProperty(required=false,value="http请求方法")
	private String httpMethod;
	/**
	 * class请求方法
	 */
	@ApiModelProperty(required=false,value="class请求方法")
	private String classMethod;
	/**
	 * 执行时长(毫秒)
	 */
	@ApiModelProperty(required=false,value="执行时长(毫秒)")
	private Long time;
	/**
	 * 返回参数
	 */
	@ApiModelProperty(required=false,value="返回参数")
	private String result;
	/**
	 * IP地址
	 */
	@ApiModelProperty(required=false,value="IP地址")
	private String ip;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 删除标志
	 */
	@ApiModelProperty(required=false,value="删除标志")
	private Integer isDel;

}
