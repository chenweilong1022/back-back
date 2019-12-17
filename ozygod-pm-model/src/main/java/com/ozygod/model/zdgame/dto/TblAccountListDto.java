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
 * 用户账号表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-16 15:29:37
 */
@Data
@TableName("tbl_account")
@ApiModel("用户账号表")
@Accessors(chain = true)
public class TblAccountListDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="用户名")
	private Long userid;
	/**
	 * 昵称
	 */
	@ApiModelProperty(required=false,value="昵称")
	private String account;
	/**
	 * 密码
	 */
	@ApiModelProperty(required=false,value="密码")
	private String pwd;
	/**
	 * 渠道
	 */
	@ApiModelProperty(required=false,value="渠道")
	private String channel;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String appChannel;
	/**
	 * 封号时间
	 */
	@ApiModelProperty(required=false,value="封号时间")
	private Date lock;
	/**
	 * 登陆时间
	 */
	@ApiModelProperty(required=false,value="登陆时间")
	private Date loginTime;
	/**
	 * 登出时间
	 */
	@ApiModelProperty(required=false,value="登出时间")
	private Date logoutTime;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;
	/**
	 * token
	 */
	@ApiModelProperty(required=false,value="token")
	private String token;
	/**
	 * token失效时间
	 */
	@ApiModelProperty(required=false,value="token失效时间")
	private Date tokenInvalidTime;
	/**
	 * 注册终端
	 */
	@ApiModelProperty(required=false,value="注册终端")
	private String platform;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String ip;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Integer vipType;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Date vipInvalidTime;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String phoneNum;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private Long saler;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String realName;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String idCode;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String lastLoginIp;
	/**
	 * 
	 */
	@ApiModelProperty(required=false,value="")
	private String createIp;
	/**
	 * 保底
	 */
	@ApiModelProperty(required=false,value="保底")
	private Float saleRate;

}
