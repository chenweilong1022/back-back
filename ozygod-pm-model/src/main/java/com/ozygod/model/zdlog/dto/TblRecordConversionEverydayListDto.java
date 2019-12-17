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
 * 每日兑换记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-17 09:59:51
 */
@Data
@TableName("tbl_record_conversion_everyday")
@ApiModel("每日兑换记录表")
@Accessors(chain = true)
public class TblRecordConversionEverydayListDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Integer id;
	/**
	 * 当前日期
	 */
	@ApiModelProperty(required=false,value="当前日期")
	private Date currentDate;
	/**
	 * 今日兑换数
	 */
	@ApiModelProperty(required=false,value="今日兑换数")
	private Integer todayConversion;
	/**
	 * 总兑换数
	 */
	@ApiModelProperty(required=false,value="总兑换数")
	private Integer totalConversion;
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
