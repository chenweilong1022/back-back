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
 * 所有监控的日志主机
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-08 16:28:15
 */
@Data
@TableName("tbl_mainframe_monitor_log")
@ApiModel("所有监控的日志主机")
@Accessors(chain = true)
public class TblMainframeMonitorLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Integer id;
	/**
	 * 主机url
	 */
	@ApiModelProperty(required=false,value="主机url")
	private String mainframe;
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

}
