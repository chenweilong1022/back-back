package com.ozygod.model.common.dto;

import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/13
 */
@Data
public class ChangeGameMoneyDto {
    private Integer gameId;
    private String who;
    private Integer money;
    private Integer tableId;
}
