package com.ozygod.model.zdconfig.dto;

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
 * 活动配置表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-13 11:20:04
 */
@Data
@TableName("tbl_active_config")
@ApiModel("活动配置表")
@Accessors(chain = true)
public class TblActiveConfigListDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Integer id;
	/**
	 * 活动id
	 */
	@ApiModelProperty(required=false,value="活动id")
	private Integer activeConfigId;
	/**
	 * 活动
	 */
	@ApiModelProperty(required=false,value="活动")
	private String active;
	/**
	 * 是否开启
	 */
	@ApiModelProperty(required=false,value="是否开启")
	private Integer enabled;
	/**
	 * 备注
	 */
	@ApiModelProperty(required=false,value="备注")
	private String note;
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
