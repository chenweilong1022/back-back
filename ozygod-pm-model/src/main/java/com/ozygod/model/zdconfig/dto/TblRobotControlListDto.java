package com.ozygod.model.zdconfig.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 机器人控制
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-12 17:06:41
 */
@Data
@TableName("tbl_robot_control")
@ApiModel("机器人控制")
@Accessors(chain = true)
public class TblRobotControlListDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Integer id;
	/**
	 * 名称
	 */
	@ApiModelProperty(required=false,value="名称")
	@TableField("`name`")
	private String name;
	/**
	 * key
	 */
	@ApiModelProperty(required=false,value="key")
	@TableField("`key`")
	private String key;
	/**
	 * 限制ip地址
	 */
	@ApiModelProperty(required=false,value="限制ip地址")
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
