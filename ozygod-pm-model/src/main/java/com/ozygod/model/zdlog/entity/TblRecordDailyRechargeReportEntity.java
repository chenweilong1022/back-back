package com.ozygod.model.zdlog.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ozygod.base.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 每日充值报表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-30 14:37:31
 */
@Data
@TableName("tbl_record_daily_recharge_report")
@ApiModel("每日充值报表")
@Accessors(chain = true)
public class TblRecordDailyRechargeReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Long id;
	/**
	 * 日期
	 */
	@ApiModelProperty(required=false,value="日期")
	@TableField(value = "`current_date`")
	@JSONField(format = "yyyy-MM-dd")
	private Date currentDate;
	/**
	 * 新用户
	 */
	@ApiModelProperty(required=false,value="新用户")
	private Integer newUser;
	/**
	 * 新机器
	 */
	@ApiModelProperty(required=false,value="新机器")
	private Integer newMachine;
	/**
	 * 新绑定
	 */
	@ApiModelProperty(required=false,value="新绑定")
	private Integer newBinding;
	/**
	 * 总注册
	 */
	@ApiModelProperty(required=false,value="总注册")
	private Integer totalRegistered;
	/**
	 * 总机器
	 */
	@ApiModelProperty(required=false,value="总机器")
	private Integer totalMachine;
	/**
	 * 登录用户
	 */
	@ApiModelProperty(required=false,value="登录用户")
	private Integer loginUser;
	/**
	 * 老用户
	 */
	@ApiModelProperty(required=false,value="老用户")
	private Integer oldUser;
	/**
	 * 登录机器
	 */
	@ApiModelProperty(required=false,value="登录机器")
	private Integer loginMachine;
	/**
	 * 老登录机器
	 */
	@ApiModelProperty(required=false,value="老登录机器")
	private Integer oldLoginMachine;
	/**
	 * 总订单
	 */
	@ApiModelProperty(required=false,value="总订单")
	private Integer totalOrder;
	/**
	 * 有效订单
	 */
	@ApiModelProperty(required=false,value="有效订单")
	private Integer effectiveOrder;
	/**
	 * 比例
	 */
	@ApiModelProperty(required=false,value="比例")
	private String proportion;
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
	 * 老用户充值
	 */
	@ApiModelProperty(required=false,value="老用户充值")
	private Integer oldUsersRecharge;
	/**
	 * 老用户充值人数
	 */
	@ApiModelProperty(required=false,value="老用户充值人数")
	private Integer oldUsersRechargeNumber;
	/**
	 * 总充值
	 */
	@ApiModelProperty(required=false,value="总充值")
	private Integer totalRecharge;
	/**
	 * 总充值人数
	 */
	@ApiModelProperty(required=false,value="总充值人数")
	private Integer totalRechargeNumber;
	/**
	 * 总返现
	 */
	@ApiModelProperty(required=false,value="总返现")
	private Integer totalBack;
	/**
	 * 总返现人数
	 */
	@ApiModelProperty(required=false,value="总返现人数")
	private Integer totalBackNumber;
	/**
	 * 次留
	 */
	@ApiModelProperty(required=false,value="次留")
	private String morrowRetained;
	/**
	 * 充值留存
	 */
	@ApiModelProperty(required=false,value="充值留存")
	private String rechargeRetained;
	/**
	 * 周留存
	 */
	@ApiModelProperty(required=false,value="周留存")
	private String weeksRetained;
	/**
	 * arpo
	 */
	@ApiModelProperty(required=false,value="arpo")
	private BigDecimal arpo;
	/**
	 * arpu
	 */
	@ApiModelProperty(required=false,value="arpu")
	private BigDecimal arpu;
	/**
	 * arppu
	 */
	@ApiModelProperty(required=false,value="arppu")
	private BigDecimal arppu;
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
	/**
	 * 昨日新用户
	 */
	@ApiModelProperty(required=false,value="昨日新用户")
	private Integer newUserYesterday;
	/**
	 * 今日留存用户
	 */
	@ApiModelProperty(required=false,value="今日留存用户")
	private Integer todayUserRetained;
	/**
	 * 昨日充值用户
	 */
	@ApiModelProperty(required=false,value="昨日充值用户")
	private Integer newUserYesterdayRecharge;
	/**
	 * 今日充值留存用户
	 */
	@ApiModelProperty(required=false,value="今日充值留存用户")
	private Integer todayUserRechargeRetained;

}
