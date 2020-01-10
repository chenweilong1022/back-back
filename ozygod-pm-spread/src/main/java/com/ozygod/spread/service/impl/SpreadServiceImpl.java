package com.ozygod.spread.service.impl;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.CommonUtil;
import com.ozygod.base.utils.HttpRequestUtil;
import com.ozygod.model.zdspread.bo.DailyGiveDiamondBO;
import com.ozygod.model.zdspread.bo.RecordDaySpreadGoldBO;
import com.ozygod.model.zdspread.bo.SpreadUserBO;
import com.ozygod.model.zdspread.dao.*;
import com.ozygod.model.zdspread.dto.SpreadDto;
import com.ozygod.model.zdspread.dto.SpreadUserDto;
import com.ozygod.model.zdspread.entity.SpreadDiamondAddRecordEntity;
import com.ozygod.spread.service.ISpreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/22
 */
@Component
public class SpreadServiceImpl implements ISpreadService {
    @Autowired
    private RecordSpreadGiveGoldEntityMapper recordSpreadGiveGoldEntityMapper;
    @Autowired
    private RecordSpreadGiveDiamondEntityMapper recordSpreadGiveDiamondEntityMapper;
    @Autowired
    private RecordDaySpreadGoldEntityMapper recordDaySpreadGoldEntityMapper;
    @Autowired
    private SpreadUserEntityMapper spreadUserEntityMapper;
    @Autowired
    private SpreadDiamondAddRecordEntityMapper spreadDiamondAddRecordEntityMapper;

    @Value("${game_url}")
    private String gameUrl;

    /**
     * 查询推广员金币提成记录
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO listRecordDaySpreadGoldByQry(SpreadDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(recordDaySpreadGoldEntityMapper.listRecordDaySpreadGoldByQry(dto));
        responseBO.setTotalCount(recordDaySpreadGoldEntityMapper.totalRecordDaySpreadGoldByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询推广员金币赠送记录
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO listRecordSpreadGiveGoldByQry(SpreadDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(recordSpreadGiveGoldEntityMapper.listRecordSpreadGiveGoldByQry(dto));
        responseBO.setTotalCount(recordSpreadGiveGoldEntityMapper.totalRecordSpreadGiveGoldByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询推广员钻石赠送记录
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO listRecordSpreadGiveDiamondByQry(SpreadDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(recordSpreadGiveDiamondEntityMapper.listRecordSpreadGiveDiamondByQry(dto));
        responseBO.setTotalCount(recordSpreadGiveDiamondEntityMapper.totalRecordSpreadGiveDiamondByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 赠送金币
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO giveGold(SpreadDto dto) {
        //赠送前校验数据
        SpreadUserDto spreadUserDto = new SpreadUserDto();
        spreadUserDto.setSpreadUserId(dto.getSpreadUserId());
        SpreadUserBO spreadUserBO = spreadUserEntityMapper.getSpreadUserByQry(spreadUserDto);

        if (spreadUserBO == null) {
            return new ResponseBO(ResponseCode.P003.getCode(), "推广员不存在");
        }
        if (spreadUserBO.getGold() < dto.getGiveGold() * 100) {
            return new ResponseBO(ResponseCode.P003.getCode(), "余额不足");
        }

        String result = HttpRequestUtil.sendGet(gameUrl + "/give_gold?spread_id=" + dto.getSpreadUserId()
        + "&user_id=" + dto.getPlayerId() + "&give_gold=" + dto.getGiveGold() * 100);
        if (CommonUtil.ValidateSuccess(result)) {
            return new ResponseBO("赠送成功");
        }
        return new ResponseBO(ResponseCode.Z001.getCode(), ResponseCode.Z001.getTitle());
    }

    /**
     * 赠送钻石
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO giveDiamond(SpreadDto dto) {
        //赠送前校验数据
        SpreadUserDto spreadUserDto = new SpreadUserDto();
        spreadUserDto.setSpreadUserId(dto.getSpreadUserId());
        SpreadUserBO spreadUserBO = spreadUserEntityMapper.getSpreadUserByQry(spreadUserDto);

        if (spreadUserBO == null) {
            return new ResponseBO(ResponseCode.P003.getCode(), "推广员不存在");
        }
        if (spreadUserBO.getDiamond() < dto.getGiveDiamond()) {
            return new ResponseBO(ResponseCode.P003.getCode(), "剩余钻石不足");
        }

//        String result = HttpRequestUtil.sendGet(gameUrl + "/add_diamond?spread_id=" + dto.getSpreadUserId()
//                + "&user_id=" + dto.getPlayerId() + "&give_diamond=" + dto.getGiveDiamond());
//        if (CommonUtil.ValidateSuccess(result)) {
//            return new ResponseBO("赠送成功");
//        }
        return new ResponseBO(ResponseCode.Z001.getCode(), ResponseCode.Z001.getTitle());
    }

    /**
     * 按日汇总金币提成记录
     *
     * @param dto
     * @return
     */
    @Override
    public List<RecordDaySpreadGoldBO> listDailyCountSpreadGoldRecordByQry(SpreadDto dto) {
        return recordDaySpreadGoldEntityMapper.listDailyCountSpreadGoldRecordByQry(dto);
    }

    /**
     * 按日汇总钻石提成记录
     *
     * @param dto
     * @return
     */
    @Override
    public List<DailyGiveDiamondBO> listDailyCountSpreadDiamondRecordByQry(SpreadDto dto) {
        return spreadDiamondAddRecordEntityMapper.listDailyCountGiveDiamondByQry(dto);
    }

    /**
     * 查询推广员钻石提成记录
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO listRecordDaySpreadDiamondByQry(SpreadDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(spreadDiamondAddRecordEntityMapper.listDiamondAddRecordByQry(dto));
        responseBO.setTotalCount(spreadDiamondAddRecordEntityMapper.totalDiamondAddRecordByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }
}
