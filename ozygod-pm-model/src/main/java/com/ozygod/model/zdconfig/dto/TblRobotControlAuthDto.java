package com.ozygod.model.zdconfig.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class TblRobotControlAuthDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * key
	 */
	@ApiModelProperty(required=false,value="key")
	@TableField("`key`")
	private String key;
	/**
	 * roomid
	 */
	@ApiModelProperty(required=false,value="roomid")
	@TableField("`roomid`")
	private String roomid;
	/**
	 * token
	 */
	@ApiModelProperty(required=false,value="token = md5(rommoid + key)")
	@TableField("`token`")
	private String token;

}
