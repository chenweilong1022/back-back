package com.ozygod.model.zdlog.entity;

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
 * 玩家在线在玩记录表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-13 14:21:15
 */
@Data
@TableName("tbl_record_account_online_playing")
@ApiModel("玩家在线在玩记录表")
@Accessors(chain = true)
public class TblRecordAccountOnlinePlayingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Integer id;
	/**
	 * 当前分钟
	 */
	@ApiModelProperty(required=false,value="当前分钟")
	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date currentMinutes;
	/**
	 * 在线数
	 */
	@ApiModelProperty(required=false,value="在线数")
	private Integer onlineNumber;
	/**
	 * 在玩数
	 */
	@ApiModelProperty(required=false,value="在玩数")
	private Integer playingNumber;
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
