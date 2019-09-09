package com.ozygod.model.zdspread.bo;

import com.ozygod.model.zdspread.entity.SpreadUserEntity;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/22
 */
@Data
public class SpreadUserBO extends SpreadUserEntity {
    private String token;
    private Integer playerCount;
    private Integer underSpreadCount;
    private String oldPassword;
    private String newPassword;
    private Integer reduceDiamond;
    private Integer managerId;
}
