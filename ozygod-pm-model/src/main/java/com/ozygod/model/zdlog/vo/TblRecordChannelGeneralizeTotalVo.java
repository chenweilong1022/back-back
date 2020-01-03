package com.ozygod.model.zdlog.vo;

import com.alibaba.fastjson.annotation.JSONField;
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
public class TblRecordChannelGeneralizeTotalVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 注册数
	 */
	@ApiModelProperty(value = "注册数",required = false)
	private Integer newUser;
	/**
	 * 注册充值数
	 */
	@ApiModelProperty(value = "注册充值数",required = false)
	private Integer newUsersRechargeNumber;
	/**
	 * 注册充值比例
	 */
	@ApiModelProperty(value = "注册充值比例",required = false)
	private String newUserRechargeRatio;
	/**
	 * 注册用户充值金额
	 */
	@ApiModelProperty(value = "注册用户充值金额",required = false)
	private Integer newUserRecharge;
	/**
	 * 总充值
	 */
	@ApiModelProperty(value = "总充值",required = false)
	private Integer todayRecharge;
	/**
	 * 总兑换
	 */
	@ApiModelProperty(value = "总兑换",required = false)
	private Integer todayConversion;
	/**
	 * 总客损金额
	 */
	@ApiModelProperty(value = "总客损金额",required = false)
	private Integer rechargePoor;
}
