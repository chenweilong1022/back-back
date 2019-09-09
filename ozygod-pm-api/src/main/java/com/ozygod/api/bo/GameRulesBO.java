package com.ozygod.api.bo;

import com.ozygod.base.bo.BaseBO;
import com.ozygod.model.zdmanage.bo.BlackWhiteListBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/15
 */
@Data
@ApiModel("游戏规则对象")
public class GameRulesBO extends BaseBO {
    /**
     * 黑白名单
     */
    @ApiModelProperty("黑白名单")
    private List<BlackWhiteListBO> blackWhiteList;
    @ApiModelProperty(value = "黑名单规则: blackListRule:{\n" +
            "\tfdshbbzl => 发红包时累计几个红包内不中雷\n" +
            "\tqdshbzl  => 抢红包时累计几个红包内一定中雷\n" +
            "}")
    private String blackListRule;
    @ApiModelProperty(value = "白名单规则: whiteListRule: {\n" +
            "\tfdshbzl  => 发红包时累计几个红包内一定中雷\n" +
            "\tqdshbbzl => 抢红包时累计几个红包内一定不中雷\n" +
            "}")
    private String whiteListRule;
}
