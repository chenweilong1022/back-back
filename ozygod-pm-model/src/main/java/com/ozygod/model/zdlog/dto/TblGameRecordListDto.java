package com.ozygod.model.zdlog.dto;

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
 * @date 2019-12-31 14:29:10
 */
@Data
@TableName("tbl_game_record")
@ApiModel("")
@Accessors(chain = true)
public class TblGameRecordListDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Integer userid;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Integer id;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Integer roomid;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Integer bet;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Integer beforeBet;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Integer result;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Integer afterBet;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Date time;

}
