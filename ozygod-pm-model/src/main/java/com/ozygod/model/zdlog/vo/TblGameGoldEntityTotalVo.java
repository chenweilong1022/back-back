package com.ozygod.model.zdlog.vo;

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
 * 金币日志
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-31 13:48:42
 */
@Data
@TableName("tbl_game_gold")
@ApiModel("金币日志")
@Accessors(chain = true)
public class TblGameGoldEntityTotalVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Long userid;
	/**
	 * 0.game; 1.flee?
	 */
	@ApiModelProperty(required=false,value="0.game; 1.flee?")
	private Integer reason;
	/**
	 *
	 */
	@ApiModelProperty(required=false,value="")
	private Integer roomid;
	/**
	 *
	 */
	@ApiModelProperty(required=false,value="")
	private Integer tableid;
	/**
	 *
	 */
	@ApiModelProperty(required=false,value="")
	private Integer seatid;
	/**
	 * 金币变化前的值
	 */
	@ApiModelProperty(required=false,value="金币变化前的值")
	private Long beginGold;
	/**
	 * 金币变化值
	 */
	@ApiModelProperty(required=false,value="金币变化值")
	private Long changeGold;
	/**
	 *
	 */
	@ApiModelProperty(required=false,value="")
	private Long taxGold;
	/**
	 * end = begin + change - tax
	 */
	@ApiModelProperty(required=false,value="end = begin + change - tax")
	private Long endGold;
	/**
	 * 备注
	 */
	@ApiModelProperty(required=false,value="备注")
	private String note;
	/**
	 *
	 */
	@ApiModelProperty(required=false,value="")
	private Date recordTime;

}
