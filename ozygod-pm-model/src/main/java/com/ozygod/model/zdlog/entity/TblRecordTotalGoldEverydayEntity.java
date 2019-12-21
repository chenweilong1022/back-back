package com.ozygod.model.zdlog.entity;

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
 * 每日总金币统计表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 11:38:17
 */
@Data
@TableName("tbl_record_total_gold_everyday")
@ApiModel("每日总金币统计表")
@Accessors(chain = true)
public class TblRecordTotalGoldEverydayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Integer id;
	/**
	 * 日期
	 */
	@ApiModelProperty(required=false,value="日期")
	private Date currentDates;
	/**
	 * 玩家身上金币
	 */
	@ApiModelProperty(required=false,value="玩家身上金币")
	private Long gold;
	/**
	 * 玩家银行金币
	 */
	@ApiModelProperty(required=false,value="玩家银行金币")
	private Long bankGold;
	/**
	 * 玩家总金币
	 */
	@ApiModelProperty(required=false,value="玩家总金币")
	private Long totalGold;
	/**
	 * 终端
	 */
	@ApiModelProperty(required=false,value="终端")
	private String platform;
	/**
	 * app渠道
	 */
	@ApiModelProperty(required=false,value="app渠道")
	private String appChannel;
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
