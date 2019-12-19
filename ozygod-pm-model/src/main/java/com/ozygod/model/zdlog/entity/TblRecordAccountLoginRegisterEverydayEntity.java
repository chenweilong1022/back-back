package com.ozygod.model.zdlog.entity;

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
 * 用户每日登录记录
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-18 18:16:55
 */
@Data
@TableName("tbl_record_account_login_register_everyday")
@ApiModel("用户每日登录记录")
@Accessors(chain = true)
public class TblRecordAccountLoginRegisterEverydayEntity implements Serializable {
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
	 * 数量
	 */
	@ApiModelProperty(required=false,value="数量")
	private Integer count;
	/**
	 * 类型/方式/来源/渠道
	 */
	@ApiModelProperty(required=false,value="类型/方式/来源/渠道")
	private Integer type;
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
