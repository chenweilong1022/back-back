package com.ozygod.model.zdmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 实时代理记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-07-25 21:37:51
 */
@Data
@TableName("tbl_agent_realtime")
@ApiModel("实时代理记录表")
@Accessors(chain = true)
public class AgentRealtimeEntity implements Serializable {


    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
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
     * 一代流水
     */
    @ApiModelProperty(required=false,value="一代流水")
    private Long firstPerformance;
    /**
     * 一代业绩
     */
    @ApiModelProperty(required=false,value="一代业绩")
    private Long firstBonus;
    /**
     * 二代流水
     */
    @ApiModelProperty(required=false,value="二代流水")
    private Long secondPerformance;
    /**
     * 二代业绩
     */
    @ApiModelProperty(required=false,value="二代业绩")
    private Long secondBonus;
    /**
     * 三代流水
     */
    @ApiModelProperty(required=false,value="三代流水")
    private Long thirdPerformance;
    /**
     * 三代业绩
     */
    @ApiModelProperty(required=false,value="三代业绩")
    private Long thirdBonus;
    /**
     * 无限代流水
     */
    @ApiModelProperty(required=false,value="无限代流水")
    private Long unlimitPerformance;
    /**
     * 无限代业绩
     */
    @ApiModelProperty(required=false,value="无限代业绩")
    private Long unlimitBonus;





}
