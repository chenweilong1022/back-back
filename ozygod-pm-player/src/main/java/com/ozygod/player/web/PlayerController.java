package com.ozygod.player.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.Constant;
import com.ozygod.model.common.bo.EmailBO;
import com.ozygod.model.zdgame.bo.PlayerAccountBO;
import com.ozygod.model.zdgame.bo.PlayerOrderBO;
import com.ozygod.model.zdgame.dto.PlayerAccountDto;
import com.ozygod.model.zdgame.dto.PlayerOrderDto;
import com.ozygod.model.zdgame.dto.RemitDto;
import com.ozygod.model.zdlog.dto.BankGoldDto;
import com.ozygod.model.zdlog.dto.PlayerLogDto;
import com.ozygod.model.zdmanage.bo.WithdrawOrderBO;
import com.ozygod.model.zdmanage.dto.BusinessDto;
import com.ozygod.model.zdspread.bo.SpreadUserBO;
import com.ozygod.player.service.IPlayerService;
import com.ozygod.player.utils.PlayerConstant;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/13
 */
@RequestMapping(value = "/players")
@RestController
@Slf4j
@Api(value = "/players", description = "玩家管理")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;

    /**
     * 查询玩家列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listPlayerAccountByQry(@RequestBody PlayerAccountDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.listPlayerAccountByQry(dto));
        responseBO.setTotalCount(playerService.totalCountPlayerAccountByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 赠送礼金
     * @param bo
     * @return
     */
    @RequestMapping(value = "/giftMoney", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO giftMoney(@RequestBody PlayerAccountBO bo) {
        int result = playerService.giftMoney(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "赠送失败");
        }
        return new ResponseBO("赠送成功");
    }

    /**
     * 设置保底
     * @param bo
     * @return
     */
    @RequestMapping(value = "/setSaleRate", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO setSaleRate(@RequestBody PlayerAccountBO bo) {
        int result = playerService.setSaleRate(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "设置失败");
        }
        return new ResponseBO("设置成功");
    }

    /**
     * 设置上级代理
     * @param bo
     * @return
     */
    @RequestMapping(value = "/setUpSpread", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO setUpSpread(@RequestBody PlayerAccountBO bo) {
        int result = playerService.setUpSpread(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "设置失败");
        }
        return new ResponseBO("设置成功");
    }

    /**
     * 发送邮件
     * @param bo
     * @return
     */
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO sendEmail(@RequestBody EmailBO bo) {
        int result = playerService.sendEmail(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "发送失败");
        }
        return new ResponseBO("发送成功");
    }

    /**
     * 查询在线玩家列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/online/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listOnlinePlayerQry(@RequestBody PlayerAccountDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.listOnlinePlayerQry(dto));
        responseBO.setTotalCount(playerService.totalOnlinePlayerByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 调整在线玩家幸运值
     * @param bo
     * @return
     */
    @RequestMapping(value = "/online/luckyRatio", method = RequestMethod.PUT, headers = Constant.API_VERSION_V1)
    public ResponseBO updatePlayerLuckyRatio(@RequestBody PlayerAccountBO bo) {
        int result = playerService.updatePlayerLuckyRatio(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "调整失败");
        }
        return new ResponseBO("调整成功");
    }

    /**
     * 查询未结算玩家列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/debt/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listDebtPlayerQry(@RequestBody PlayerAccountDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.listDebtPlayerQry(dto));
        responseBO.setTotalCount(playerService.totalDebtPlayerByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询玩家详情
     * @param playerId
     * @return
     */
    @RequestMapping(value = "/{playerId}", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO getPlayerAccountById(@PathVariable Integer playerId) {
        PlayerAccountBO playerAccountBO = playerService.getPlayerAccountById(playerId);
        if (playerAccountBO == null) {
            return new ResponseBO(ResponseCode.Q001.getCode(), "当前玩家详情不存在!");
        }
        return new ResponseBO(playerAccountBO);
    }

    /**
     * 查询玩家详情
     * @param showId
     * @return
     */
    @RequestMapping(value = "/show/{showId}", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO getPlayerAccountByShowId(@PathVariable Integer showId) {
        PlayerAccountBO playerAccountBO = playerService.getPlayerAccountByShowId(showId);
        if (playerAccountBO == null) {
            return new ResponseBO(ResponseCode.Q001.getCode(), "当前玩家详情不存在!");
        }
        return new ResponseBO(playerAccountBO);
    }

    /**
     * 查询玩家银行日志列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/bank/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listPlayerBankLogByQry(@RequestBody BankGoldDto dto) {
        return playerService.listPlayerBankLogByQry(dto);
    }

    /**
     * 查询玩家充值订单列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/order/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listPlayerOrderByQry(@RequestBody PlayerOrderDto dto) {
        return playerService.listPlayerOrderByQry(dto);
    }

    /**
     * 查询玩家充值订单列表
     * @param bo
     * @return
     */
    @RequestMapping(value = "/order/modify", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO updateOrder(@RequestBody PlayerOrderBO bo) {
        int result = playerService.updateOrder(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "处理失败");
        }
        return new ResponseBO("处理成功");
    }

    /**
     * 查询玩家游戏日志列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/gameLog/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listPlayerGameLogByQry(@RequestBody PlayerLogDto dto) {
        return playerService.listPlayerGameLogByQry(dto);
    }

    /**
     * 查询玩家钻石游戏日志列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/diamondGameLog/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listPlayerDiamondLogByQry(@RequestBody PlayerLogDto dto) {
        return playerService.listPlayerDiamondLogByQry(dto);
    }

    /**
     * 重置玩家密码
     * @param playerId
     * @return
     */
    @RequestMapping(value = "/{playerId}/resetPassword", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO resetPlayerPassword(@PathVariable Integer playerId, @RequestBody PlayerAccountBO bo) {
        int result = playerService.resetPlayerPassword(playerId, bo.getManagerId());
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "重置玩家密码失败");
        }
        return new ResponseBO("重置玩家密码成功");
    }

    /**
     * 重置玩家银行密码
     * @param playerId
     * @return
     */
    @RequestMapping(value = "/{playerId}/bank/resetPassword", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO resetPlayerBankPassword(@PathVariable Integer playerId, @RequestBody PlayerAccountBO bo) {
        int result = playerService.resetPlayerBankPassword(playerId, bo.getManagerId());
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "重置银行密码失败");
        }
        return new ResponseBO("重置银行密码成功");
    }

    /**
     * 查询玩家冻结记录
     * @param playerId
     * @return
     */
    @RequestMapping(value = "/{playerId}/freezeLog", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listFreezeUserRecordById(@PathVariable Integer playerId) {
        return new ResponseBO(playerService.listFreezeUserRecordById(playerId));
    }

    /**
     * 冻结玩家
     * @param playerId
     * @return
     */
    @RequestMapping(value = "/{playerId}/freeze", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO freezePlayer(@PathVariable Integer playerId, @RequestBody PlayerAccountBO bo) {
        bo.setUserid((long) playerId);
        int result = playerService.freezePlayer(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "冻结失败");
        }
        return new ResponseBO("冻结成功");
    }

    /**
     * 批量冻结玩家
     * @param bo
     * @return
     */
    @RequestMapping(value = "/batchFreeze", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO batchFreezePlayer(@RequestBody PlayerAccountBO bo) {
        int result = playerService.batchFreezePlayer(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "批量封号失败");
        }
        return new ResponseBO("批量封号成功");
    }

    /**
     * 解冻玩家
     * @param playerId
     * @return
     */
    @RequestMapping(value = "/{playerId}/unfreeze", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO unfreezePlayer(@PathVariable Integer playerId, @RequestBody PlayerAccountBO bo) {
        bo.setUserid((long) playerId);
        int result = playerService.unfreezePlayer(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "解冻失败");
        }
        return new ResponseBO("解冻成功");
    }

    /**
     * 转换为代理
     * @param playerId
     * @return
     */
    @RequestMapping(value = "/{playerId}/transferToSpread", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO transferToSpread(@PathVariable Integer playerId, @RequestBody PlayerAccountBO accountBO) {
        SpreadUserBO bo = new SpreadUserBO();
        bo.setId(playerId);
        bo.setManagerId(accountBO.getManagerId());
        int result = playerService.transferToSpreadUser(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "转换失败");
        }
        return new ResponseBO("转换成功");
    }

    /**
     * 查询玩家游戏输赢记录
     * @param playerId
     * @return
     */
    @RequestMapping(value = "/{playerId}/gameStatistics", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listPlayerGameRecordByQry(@PathVariable Integer playerId) {
        PlayerAccountDto dto = new PlayerAccountDto();
        dto.setPlayerId(playerId);
        return new ResponseBO(playerService.listPlayerGameRecordByQry(dto));
    }

    /**
     * 查询相同地址玩家
     * @param playerId
     * @return
     */
    @RequestMapping(value = "/{playerId}/sameAddress", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listSameAddressPlayerById(@PathVariable Integer playerId) {
        return new ResponseBO(playerService.listSameAddressPlayerById(playerId));
    }

    /**
     * 查询玩家私人房输赢统计
     * @param dto
     * @return
     */
    @RequestMapping(value = "/statistics/privateGame", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listPrivateGameStatisticsByQry(@RequestBody PlayerAccountDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.listPrivateGameStatisticsByQry(dto));
        responseBO.setTotalCount(playerService.totalPrivateGameStatisticsByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询玩家私人房输赢记录
     * @param dto
     * @return
     */
    @RequestMapping(value = "/record/privateGame", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listPlayerPrivateGameByQry(@RequestBody PlayerAccountDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.listPlayerPrivateGameByQry(dto));
        responseBO.setTotalCount(playerService.totalPlayerPrivateGameByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询玩家提现订单列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/withdraw/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listWithdrawOrderByQry(@RequestBody BusinessDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.listWithdrawOrderByQry(dto));
        responseBO.setTotalCount(playerService.totalWithdrawOrderbyQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 更新玩家提现订单
     * @param bo
     * @return
     */
    @RequestMapping(value = "/withdraw/modify", method = RequestMethod.PUT, headers = Constant.API_VERSION_V1)
    public ResponseBO updateWithdrawOrder(@RequestBody WithdrawOrderBO bo) {
        int result = playerService.updateWithdrawOrder(bo);
        String msg = "处理成功";
        if (result == 0) {
            msg = "处理失败";
            return new ResponseBO(ResponseCode.U001.getCode(), msg);
        }
        return new ResponseBO(ResponseCode.S000.getCode(), msg);
    }

    /**
     * 查询玩家财富排行
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wealthRank", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listPlayerWealthRankByQry(@RequestBody PlayerAccountDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.listPlayerWealthRankByQry(dto));
        responseBO.setTotalCount(playerService.totalPlayerWealthRankByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 给指定玩家划账(金币)
     * @param dto
     * @return
     */
    @RequestMapping(value = "/remitGold", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO remitGold(@RequestBody RemitDto dto) {
        // 人工提现时先检查该玩家是否已经充值
        if (dto.getRemitGold() < 0) {
            PlayerAccountBO accountBO = playerService.getPlayerAccountById(dto.getPlayerId());
            if (accountBO.getGold() != null && accountBO.getGold() < Math.abs(dto.getRemitGold() * 100)){
                return new ResponseBO(ResponseCode.U001.getCode(), "出款失败，玩家余额不足");
            }
            int count = playerService.validateUserRechargeState(dto.getShowId());
            if (count == 0) {
                return new ResponseBO(ResponseCode.U001.getCode(), "出款失败，该玩家从未充值过!");
            }
        }

        int result = playerService.remitGold(dto);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "划账失败");
        }
        return new ResponseBO("划账成功");
    }

    /**
     * 给指定玩家划账(钻石)
     * @param dto
     * @return
     */
    @RequestMapping(value = "/remitDiamond", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO remitDiamond(@RequestBody RemitDto dto) {
        int result = playerService.remitDiamond(dto);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "划账失败");
        }
        return new ResponseBO("划账成功");
    }

    /**
     * 查询玩家划账日志列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/remitGold/record", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listRemitGoldRecordByQry(@RequestBody PlayerLogDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.listRemitGoldRecordByQry(dto));
        responseBO.setTotalCount(playerService.totalRemitGoldRecordByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询玩家划账总额
     * @param dto
     * @return
     */
    @RequestMapping(value = "/remitGold/record/total", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO getTotalRemitGoldByQry(@RequestBody PlayerLogDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.getTotalRemitGoldByQry(dto));
        return responseBO;
    }

    /**
     * 查询玩家划账日志列表(钻石)
     * @param dto
     * @return
     */
    @RequestMapping(value = "/remitDiamond/record", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listRemitDiamondRecordByQry(@RequestBody PlayerLogDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.listRemitDiamondRecordByQry(dto));
        responseBO.setTotalCount(playerService.totalRemitDiamondRecordByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询玩家划账总额(钻石)
     * @param dto
     * @return
     */
    @RequestMapping(value = "/remitDiamond/record/total", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO getTotalRemitDiamondByQry(@RequestBody PlayerLogDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.getTotalRemitDiamondByQry(dto));
        return responseBO;
    }

    /**
     * 查询玩家输赢明细列表
     * @return
     */
    @RequestMapping(value = "/winningDetail", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listUserGameDetailByQry(@RequestBody PlayerLogDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(playerService.listUserGameDetailByQry(dto));
        responseBO.setTotalCount(playerService.totalUserGameDetailByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

}
