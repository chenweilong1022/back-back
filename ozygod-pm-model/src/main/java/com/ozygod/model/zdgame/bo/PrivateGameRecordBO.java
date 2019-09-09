package com.ozygod.model.zdgame.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.model.zdgame.entity.PrivateGameRecordEntity;
import lombok.Data;

import java.util.Date;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/10/31
 */
@Data
public class PrivateGameRecordBO extends PrivateGameRecordEntity {
    private String nickname;
    @JSONField(format = "yyyy-MM-dd")
    private Date dailyDate;
}
