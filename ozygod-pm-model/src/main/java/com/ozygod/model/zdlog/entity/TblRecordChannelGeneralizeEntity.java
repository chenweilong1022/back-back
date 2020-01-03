package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
 * 每日渠道推广统计
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 13:54:15
 */
@Data
@TableName("tbl_record_channel_generalize")
@ApiModel("每日渠道推广统计")
@Accessors(chain = true)
public class TblRecordChannelGeneralizeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Long id;
	/**
	 * 渠道
	 */
	@ApiModelProperty(required=false,value="渠道")
	private String channel;
	/**
	 * 日期
	 */
	@ApiModelProperty(required=false,value="日期")
	@JSONField(format = "yyyy-MM-dd")
	private Date currentDates;
	/**
	 * 推广id
	 */
	@ApiModelProperty(required=false,value="推广id")
	private Long agentUserId;
	/**
	 * 新用户
	 */
	@ApiModelProperty(required=false,value="新用户")
	private Integer newUser;
	/**
	 * 新设备
	 */
	@ApiModelProperty(required=false,value="新设备")
	private Integer newEquipment;
	/**
	 * 新用户游戏
	 */
	@ApiModelProperty(required=false,value="新用户游戏")
	private Integer newUserGame;
	/**
	 * 新设备游戏
	 */
	@ApiModelProperty(required=false,value="新设备游戏")
	private Integer newEquipmentGame;
	/**
	 * 新用户游戏比
	 */
	@ApiModelProperty(required=false,value="新用户游戏比")
	private String newUserGameRatio;
	/**
	 * 新设备游戏比
	 */
	@ApiModelProperty(required=false,value="新设备游戏比")
	private String newEquipmentGameRatio;
	/**
	 * 活跃用户
	 */
	@ApiModelProperty(required=false,value="活跃用户")
	private Integer activeUsers;
	/**
	 * 活跃设备
	 */
	@ApiModelProperty(required=false,value="活跃设备")
	private Integer activeEquipment;
	/**
	 * 新用户充值
	 */
	@ApiModelProperty(required=false,value="新用户充值")
	private Integer newUserRecharge;
	/**
	 * 新用户充值人数
	 */
	@ApiModelProperty(required=false,value="新用户充值人数")
	private Integer newUsersRechargeNumber;
	/**
	 * 新用户充值比例
	 */
	@ApiModelProperty(required=false,value="新用户充值比例")
	private String newUserRechargeRatio;
	/**
	 * 新用户机器比例
	 */
	@ApiModelProperty(required=false,value="新用户机器比例")
	private String newUserEquipmentRatio;
	/**
	 * 新用户兑换
	 */
	@ApiModelProperty(required=false,value="新用户兑换")
	private Integer newUserConversion;
	/**
	 * 新用户兑换人
	 */
	@ApiModelProperty(required=false,value="新用户兑换人")
	private Integer newUserConversionNumber;
	/**
	 * 今日充值
	 */
	@ApiModelProperty(required=false,value="今日充值")
	private Integer todayRecharge;
	/**
	 * 今日充值人数
	 */
	@ApiModelProperty(required=false,value="今日充值人数")
	private Integer todayRechargeNumber;
	/**
	 * 今日兑换
	 */
	@ApiModelProperty(required=false,value="今日兑换")
	private Integer todayConversion;
	/**
	 * 今日兑换人数
	 */
	@ApiModelProperty(required=false,value="今日兑换人数")
	private Integer todayConversionNumber;
	/**
	 * 今日充值兑换比例
	 */
	@ApiModelProperty(required=false,value="今日充值兑换比例")
	private String todayRechargeConversionRatio;
	/**
	 * 今日充值兑换人数比例
	 */
	@ApiModelProperty(required=false,value="今日充值兑换人数比例")
	private String todayRechargeConversionRatioNumber;
	/**
	 * 充值差
	 */
	@ApiModelProperty(required=false,value="充值差")
	private Integer rechargePoor;
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
