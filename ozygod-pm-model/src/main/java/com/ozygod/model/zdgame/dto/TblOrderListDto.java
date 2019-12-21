package com.ozygod.model.zdgame.dto;

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
 * @date 2019-12-19 14:18:44
 */
@Data
@TableName("tbl_order")
@ApiModel("")
@Accessors(chain = true)
public class TblOrderListDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="自增ID")
	private Long id;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(required=false,value="用户ID")
	private Long userid;
	/**
	 * 商品ID
	 */
	@ApiModelProperty(required=false,value="商品ID")
	private String goodsId;
	/**
	 * 支付渠道。alipay，支付宝；weixin，微信；apple，苹果
	 */
	@ApiModelProperty(required=false,value="支付渠道。alipay，支付宝；weixin，微信；apple，苹果")
	private String channel;
	/**
	 * 第三方订单号
	 */
	@ApiModelProperty(required=false,value="第三方订单号")
	private String orderId;
	/**
	 * 实际支付人民币
	 */
	@ApiModelProperty(required=false,value="实际支付人民币")
	private Integer money;
	/**
	 * 状态。1，下单；2，支付成功；3，发送金币完成；4，失效；
	 */
	@ApiModelProperty(required=false,value="状态。1，下单；2，支付成功；3，发送金币完成；4，失效；")
	private Integer state;
	/**
	 * 创建订单时间
	 */
	@ApiModelProperty(required=false,value="创建订单时间")
	private Date createTime;
	/**
	 * 失效时间
	 */
	@ApiModelProperty(required=false,value="失效时间")
	private Date invalidTime;
	/**
	 * 支付成功时间
	 */
	@ApiModelProperty(required=false,value="支付成功时间")
	private Date payTime;
	/**
	 * 完成时间
	 */
	@ApiModelProperty(required=false,value="完成时间")
	private Date completeTime;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String type;

}
