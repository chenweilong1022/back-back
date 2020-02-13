package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 每日闯关记录领取表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-12 17:05:11
 */
@Data
@TableName("tbl_daily_adventure_award_get_record")
@ApiModel("每日闯关记录领取表")
@Accessors(chain = true)
public class TblDailyAdventureAwardGetRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Integer id;
	/**
	 * 用户id
	 */
	@ApiModelProperty(required=false,value="用户id")
	private Long userid;
	/**
	 * 关卡
	 */
	@ApiModelProperty(required=false,value="关卡")
	private Integer checkpoint;
	/**
	 * 有效投注
	 */
	@ApiModelProperty(required=false,value="有效投注")
	private Integer effectiveBetting;
	/**
	 * 奖励
	 */
	@ApiModelProperty(required=false,value="奖励")
	private Integer reward;
	/**
	 * 当前投注
	 */
	@ApiModelProperty(required=false,value="当前投注")
	private Integer currentBetting;
	/**
	 * 备注
	 */
	@ApiModelProperty(required=false,value="备注")
	private String note;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 删除标志
	 */
	@ApiModelProperty(required=false,value="删除标志")
	private Integer isDel;

}
