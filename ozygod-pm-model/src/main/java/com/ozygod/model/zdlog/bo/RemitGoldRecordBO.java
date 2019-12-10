package com.ozygod.model.zdlog.bo;

import com.ozygod.model.zdlog.entity.ManagerRemitGoldRecordEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/28
 */
@Data
public class RemitGoldRecordBO extends ManagerRemitGoldRecordEntity {
    @ApiModelProperty("管理员账号")
    private String managerAccount;
    @ApiModelProperty("玩家昵称")
    private String nickname;
    @ApiModelProperty("显示id")
    private Long showId;
}
