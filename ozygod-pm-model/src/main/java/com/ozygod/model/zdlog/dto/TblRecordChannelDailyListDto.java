package com.ozygod.model.zdlog.dto;

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
 * 渠道日报
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-31 11:11:19
 */
@Data
@TableName("tbl_record_channel_daily")
@ApiModel("渠道日报")
@Accessors(chain = true)
public class TblRecordChannelDailyListDto extends BaseDto implements Serializable {
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
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;
	/**
	 * 删除标志
	 */
	@ApiModelProperty(required=false,value="删除标志")
	private Integer isDel;
	/**
	 * 营收
	 */
	@ApiModelProperty(required=false,value="营收")
	private Integer profit;
	/**
	 * 充值
	 */
	@ApiModelProperty(required=false,value="充值")
	private Integer recharge;
	/**
	 * 兑换
	 */
	@ApiModelProperty(required=false,value="兑换")
	private Integer conversion;
	/**
	 * 注册用户数
	 */
	@ApiModelProperty(required=false,value="注册用户数")
	private Integer registerUsers;
	/**
	 * 登录用户数
	 */
	@ApiModelProperty(required=false,value="登录用户数")
	private Integer loginUsers;
	/**
	 * 总税收
	 */
	@ApiModelProperty(required=false,value="总税收")
	private Integer totalRevenue;
	/**
	 * 游戏记录
	 */
	@ApiModelProperty(required=false,value="游戏记录")
	private Integer gameRecord;
	/**
	 * 最后更新时间
	 */
	@ApiModelProperty(required=false,value="最后更新时间")
	private Date lastUpdateTime;

}
