package com.ozygod.model.zdgame.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-13 17:20:00
 */
@Data
@TableName("tbl_player_location")
@ApiModel("")
@Accessors(chain = true)
public class TblPlayerLocationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Long userid;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String room;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	@TableField("table_")
	private String table;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Float val;

}
