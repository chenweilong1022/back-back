package com.ozygod.model.zdmanage.dto;

import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/16
 */
@Data
public class GameRuleDto {
    /**
     * 当事人id
     */
    private Integer partyId;
    /**
     * 规则类型
     */
    private Integer ruleType;
}
