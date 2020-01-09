package com.ozygod.model.zdmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 监听器表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-08 16:28:15
 */
@Data
@TableName("tbl_monitor")
@ApiModel("监听器表")
@Accessors(chain = true)
public class TblMonitorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Integer id;
	/**
	 * 日志路径
	 */
	@ApiModelProperty(required=false,value="日志路径")
	private String logFile;
	/**
	 * 监控关键字
	 */
	@ApiModelProperty(required=false,value="监控关键字")
	private String monitorKeyword;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;
	/**
	 * 删除标志
	 */
	@ApiModelProperty(required=false,value="删除标志")
	private Integer isDel;
	/**
	 * 发送邮箱
	 */
	@ApiModelProperty(required=false,value="发送邮箱")
	private String email;

}
