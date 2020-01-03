package com.ozygod.model.zdmanage.entity;

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
 * @date 2020-01-02 14:17:45
 */
@Data
@TableName("tbl_agent_realtime")
@ApiModel("")
@Accessors(chain = true)
public class TblAgentRealtimeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
//	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Long userId;
	/**
	 * 上级玩家id
	 */
	@ApiModelProperty(required=false,value="上级玩家id")
	private Long superId;
	/**
	 * 总业绩
	 */
	@ApiModelProperty(required=false,value="总业绩")
	private Long totalPerformance;
	/**
	 * 代理等级
	 */
	@ApiModelProperty(required=false,value="代理等级")
	private Integer agentLevel;
	/**
	 * 提成比率
	 */
	@ApiModelProperty(required=false,value="提成比率")
	private Integer ratio;
	/**
	 * 当前返佣
	 */
	@ApiModelProperty(required=false,value="当前返佣")
	private Long rebate;
	/**
	 * 自营业绩
	 */
	@ApiModelProperty(required=false,value="自营业绩")
	private Long performance;
	/**
	 * 直属业绩
	 */
	@ApiModelProperty(required=false,value="直属业绩")
	private Long directPerformance;
	/**
	 * 团队业绩
	 */
	@ApiModelProperty(required=false,value="团队业绩")
	private Long teamPerformance;
	/**
	 * 下属人数
	 */
	@ApiModelProperty(required=false,value="下属人数")
	private Integer underCount;

}
