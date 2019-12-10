package com.ozygod.model.zdgame.bo;

import com.alibaba.fastjson.annotation.JSONField;
import com.ozygod.model.zdgame.entity.WithDrawRecordEntity;
import lombok.Data;

import java.util.Date;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/28
 */
@Data
public class PlayerWithDrawRecordBO extends WithDrawRecordEntity {
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dealTime;
    private String nickName;
}
