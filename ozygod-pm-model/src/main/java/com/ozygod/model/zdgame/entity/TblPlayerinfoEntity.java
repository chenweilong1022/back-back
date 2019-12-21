package com.ozygod.model.zdgame.entity;

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
 * 用户数据表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 11:39:15
 */
@Data
@TableName("tbl_playerinfo")
@ApiModel("用户数据表")
@Accessors(chain = true)
public class TblPlayerinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Long userid;
	/**
	 * 昵称
	 */
	@ApiModelProperty(required=false,value="昵称")
	private String nickname;
	/**
	 * 保险箱密码
	 */
	@ApiModelProperty(required=false,value="保险箱密码")
	private String secondPwd;
	/**
	 * 性别。1-female；2-male
	 */
	@ApiModelProperty(required=false,value="性别。1-female；2-male")
	private Integer sex;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Long gold;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Long bankGold;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Long honor;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Long diamond;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(required=false,value="更新时间")
	private Date lastUpdate;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String imageid;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Integer owned;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Long showId;
	/**
	 * 是否赠送礼金,0未赠送，1已赠送
	 */
	@ApiModelProperty(required=false,value="是否赠送礼金,0未赠送，1已赠送")
	private Integer sendGift;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Integer recharge;

}
