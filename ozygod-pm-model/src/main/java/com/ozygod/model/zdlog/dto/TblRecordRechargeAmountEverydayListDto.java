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
 * 每日充值记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 15:27:02
 */
@Data
@TableName("tbl_record_recharge_amount_everyday")
@ApiModel("每日充值记录表")
@Accessors(chain = true)
public class TblRecordRechargeAmountEverydayListDto extends BaseDto implements Serializable {
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
	 * 周
	 */
	@ApiModelProperty(required=false,value="周")
	private Integer week;
	/**
	 * 月
	 */
	@ApiModelProperty(required=false,value="月")
	private Integer month;
	/**
	 * 金额
	 */
	@ApiModelProperty(required=false,value="金额")
	private Integer money;
	/**
	 * 支付渠道。alipay，支付宝；weixin，微信；apple，苹果
	 */
	@ApiModelProperty(required=false,value="支付渠道。alipay，支付宝；weixin，微信；apple，苹果")
	private String payChannel;
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
