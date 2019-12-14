package com.ozygod.model.zdconfig.entity;

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
 * 系统ip白名单配置
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-14 21:50:40
 */
@Data
@TableName("tbl_ip_white_list")
@ApiModel("系统ip白名单配置")
@Accessors(chain = true)
public class TblIpWhiteListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Integer id;
	/**
	 * 白名单ip
	 */
	@ApiModelProperty(required=false,value="白名单ip")
	private String ip;
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
