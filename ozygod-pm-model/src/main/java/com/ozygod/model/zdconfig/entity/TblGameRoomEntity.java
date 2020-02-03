package com.ozygod.model.zdconfig.entity;

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
 * 
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-03 10:27:37
 */
@Data
@TableName("tbl_game_room")
@ApiModel("")
@Accessors(chain = true)
public class TblGameRoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Integer roomid;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Integer gameid;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String roomName;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Boolean isClub;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String roomPwd;

}
