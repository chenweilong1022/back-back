package com.ozygod.model.zdmanage.dto;

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
 * @date 2019-12-30 18:00:03
 */
@Data
@TableName("tbl_withdraw_order")
@ApiModel("")
@Accessors(chain = true)
public class TblWithdrawOrderListDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Long id;
	/**
	 * 玩家id
	 */
	@ApiModelProperty(required=false,value="玩家id")
	private Integer userid;
	/**
	 * 订单状态，0未处理，1已审核，2已打款，3已取消
	 */
	@ApiModelProperty(required=false,value="订单状态，0未处理，1已审核，2已打款，3已取消")
	private Integer state;
	/**
	 * 0支付宝，1手工提现
	 */
	@ApiModelProperty(required=false,value="0支付宝，1手工提现")
	private Integer type;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String account;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String realname;
	/**
	 * 提现金额
	 */
	@ApiModelProperty(required=false,value="提现金额")
	private Integer amount;
	/**
	 * 手续费
	 */
	@ApiModelProperty(required=false,value="手续费")
	private Integer tax;
	/**
	 * 备注
	 */
	@ApiModelProperty(required=false,value="备注")
	private String note;
	/**
	 * 审核人id
	 */
	@ApiModelProperty(required=false,value="审核人id")
	private Integer approvalId;
	/**
	 * 完成时间
	 */
	@ApiModelProperty(required=false,value="完成时间")
	private Date completetime;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;

}
