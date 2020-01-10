package com.ozygod.spread.service;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdspread.bo.DailyGiveDiamondBO;
import com.ozygod.model.zdspread.bo.RecordDaySpreadGoldBO;
import com.ozygod.model.zdspread.dto.SpreadDto;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/22
 */
public interface ISpreadService {
    /**
     * 查询推广员金币赠送记录
     * @param dto
     * @return
     */
    ResponseBO listRecordSpreadGiveGoldByQry(SpreadDto dto);

    /**
     * 查询推广员钻石赠送记录
     * @param dto
     * @return
     */
    ResponseBO listRecordSpreadGiveDiamondByQry(SpreadDto dto);

    /**
     * 查询推广员金币提成记录
     * @param dto
     * @return
     */
    ResponseBO listRecordDaySpreadGoldByQry(SpreadDto dto);

    /**
     * 查询推广员钻石提成记录
     * @param dto
     * @return
     */
    ResponseBO listRecordDaySpreadDiamondByQry(SpreadDto dto);

    /**
     * 赠送金币
     * @param dto
     * @return
     */
    ResponseBO giveGold(SpreadDto dto);

    /**
     * 赠送钻石
     * @param dto
     * @return
     */
    ResponseBO giveDiamond(SpreadDto dto);

    /**
     * 按日汇总金币提成记录
     * @param dto
     * @return
     */
    List<RecordDaySpreadGoldBO> listDailyCountSpreadGoldRecordByQry(SpreadDto dto);

    /**
     * 按日汇总钻石提成记录
     * @param dto
     * @return
     */
    List<DailyGiveDiamondBO> listDailyCountSpreadDiamondRecordByQry(SpreadDto dto);
}
