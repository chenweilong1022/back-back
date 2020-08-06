package com.ozygod.model.zdmanage.entity;

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
 * 代理结算记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-08-06 19:54:59
 */
@Data
@TableName("tbl_agent_record")
@ApiModel("代理结算记录表")
@Accessors(chain = true)
public class AgentRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(required=false,value="id")
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(required=false,value="用户id")
    private Long userId;
    /**
     * 上级id
     */
    @ApiModelProperty(required=false,value="上级id")
    private Long superId;
    /**
     * 直属下线人数
     */
    @ApiModelProperty(required=false,value="直属下线人数")
    private Integer underCount;
    /**
     * 结算时间
     */
    @ApiModelProperty(required=false,value="结算时间")
    private Date agentTime;
    /**
     * 期号，月日期
     */
    @ApiModelProperty(required=false,value="期号，月日期")
    private Integer period;
    /**
     * 一代流水
     */
    @ApiModelProperty(required=false,value="一代流水")
    private Long firstPerformance;
    /**
     * 二代流水
     */
    @ApiModelProperty(required=false,value="二代流水")
    private Long secondPerformance;
    /**
     * 三代流水
     */
    @ApiModelProperty(required=false,value="三代流水")
    private Long thirdPerformance;
    /**
     * 无限代流水
     */
    @ApiModelProperty(required=false,value="无限代流水")
    private Long unlimitPerformance;
    /**
     * 一代佣金
     */
    @ApiModelProperty(required=false,value="一代佣金")
    private Long firstBonus;
    /**
     * 二代佣金
     */
    @ApiModelProperty(required=false,value="二代佣金")
    private Long secondBonus;
    /**
     * 三代佣金
     */
    @ApiModelProperty(required=false,value="三代佣金")
    private Long thirdBonus;
    /**
     * 无限代佣金
     */
    @ApiModelProperty(required=false,value="无限代佣金")
    private Long unlimitBonus;

}
