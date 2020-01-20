package com.ozygod.model.zdlog.entity;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ozygod.base.annotations.MoneyField;
import com.ozygod.base.dto.BaseDto;
import com.ozygod.base.enums.MoneyFormatType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 金币日志
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-20 11:06:15
 */
@Data
@TableName("tbl_game_gold")
@ApiModel("金币日志")
@Accessors(chain = true)
public class TblGameGoldEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@ApiModelProperty(required=false,value="")
	private Long userid;
	/**
	 * 0.game; 1.flee?
	 */
	@ApiModelProperty(required=false,value="0.game; 1.flee?")
	private String reason;
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
	@MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
	private Long beginGold;
	/**
	 * 金币变化值
	 */
	@ApiModelProperty(required=false,value="金币变化值")
	@MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
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
	@MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date recordTime;
	/**
	 *
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Integer id;
	/**
	 * 短时间
	 * @return
	 */
	public String getRecordTimeCurrentTime() {
		return DateUtil.formatDate(this.recordTime);
	}

}
