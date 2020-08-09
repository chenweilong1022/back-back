package com.ozygod.model.zdgame.bo;

import com.ozygod.base.annotations.MoneyField;
import com.ozygod.base.enums.MoneyFormatType;
import com.ozygod.model.zdconfig.vo.PlayersWinLoseVO;
import com.ozygod.model.zdgame.entity.AccountEntity;
import com.ozygod.model.zdlog.bo.GameWinningDetailBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/18
 */
@Data
@ApiModel("玩家帐号业务类")
public class PlayerAccountBO extends AccountEntity {
    @ApiModelProperty(value = "渠道名")
    private String channelName;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "金币")
    private Long gold;
    @ApiModelProperty(value = "银行金币")
    private Long bankGold;
    @ApiModelProperty(value = "金币总数")
    private Long totalGold;
    @ApiModelProperty(value = "荣誉点")
    private Long honor;
    @ApiModelProperty(value = "钻石")
    private Long diamond;
    @ApiModelProperty(value = "总充值金额")
    private Long totalPay;
    @ApiModelProperty(value = "总提现金额")
    private Long totalWithdraw;
    @ApiModelProperty(value = "管理员ID")
    private Integer managerId;
    @ApiModelProperty(value = "操作原因")
    private String reason;
    @ApiModelProperty(value = "开始玩家ID")
    private Long startPlayerId;
    @ApiModelProperty(value = "结束玩家ID")
    private Long endPlayerId;
    @ApiModelProperty(value = "玩家IP归属")
    private String ipAttr;
    @ApiModelProperty(value = "游戏名称")
    private String room;
    @ApiModelProperty(value = "游戏房间名称")
    private String roomName;
    @ApiModelProperty(value = "游戏房间号")
    private String tableId;
    @ApiModelProperty("推广用户id")
    private Integer spreadUserId;
    @ApiModelProperty("推广提成比率")
    private Integer spreadRatio;
    @ApiModelProperty("今日返佣")
    private Long todayRebate;
    @ApiModelProperty("本周返佣")
    private Long thisWeekRebate;
    @ApiModelProperty("本月返佣")
    private Long thisMonthRebate;
    @ApiModelProperty("欠款")
    private Long arrears;
    @ApiModelProperty("玩家幸运值")
    private Double luckyRatio;
    @ApiModelProperty("当日输赢")
    private Long todayWinningGold;
    @ApiModelProperty("总输赢")
    private Long totalWinningGold;
    @ApiModelProperty("是否赠送礼金")
    private Integer sendGift;
    @ApiModelProperty("保底推广比率")
    private Double saleRate;
    @ApiModelProperty("显示id")
    private Long showId;
    @ApiModelProperty("充值标识")
    private Integer rechargeFlag;
    @ApiModelProperty("代理昵称")
    private String agentNickname;
    @ApiModelProperty("今日充值")
    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private long todayRechargeGold;
    @ApiModelProperty("总充值")
    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private long totalRechargeGold;
    @ApiModelProperty("今日提现")
    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private long todayWithdrawGold;
    @ApiModelProperty("总提现")
    @MoneyField(moneyFormatTypes = {MoneyFormatType.DIV})
    private long totalWithdrawGold;
    @ApiModelProperty("游戏输赢")
    private PlayersWinLoseVO playersWinLoseVO;
    @ApiModelProperty("总的游戏输赢")
    private PlayersWinLoseVO totalPlayersWinLoseVO;
    @ApiModelProperty("合计类型")
    private String type;
}
