package com.ozygod.spread.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.utils.Constant;
import com.ozygod.model.zdspread.dto.SpreadDto;
import com.ozygod.spread.service.ISpreadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/22
 */
@RequestMapping(value = "/spreads")
@RestController
@Api(value = "/spreads", description = "推广业务处理类")
public class SpreadController implements Serializable {
    private static final long serialVersionUID = -7476786157005749590L;

    @Autowired
    private ISpreadService spreadService;

    /**
     * 查询推广员金币赠送记录
     * @param spreadDto
     * @return
     */
    @RequestMapping(value = "/record/giveGold/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listRecordSpreadGiveGoldByQry(@RequestBody SpreadDto spreadDto) {
        return spreadService.listRecordSpreadGiveGoldByQry(spreadDto);
    }

    /**
     * 查询推广员钻石赠送记录
     * @param spreadDto
     * @return
     */
    @RequestMapping(value = "/record/giveDiamond/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listRecordSpreadGiveDiamondByQry(@RequestBody SpreadDto spreadDto) {
        return spreadService.listRecordSpreadGiveDiamondByQry(spreadDto);
    }

    /**
     * 查询推广员金币提成记录
     * @param spreadDto
     * @return
     */
    @RequestMapping(value = "/record/spreadGold/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listRecordDaySpreadGoldByQry(@RequestBody SpreadDto spreadDto) {
        return spreadService.listRecordDaySpreadGoldByQry(spreadDto);
    }

    /**
     * 查询推广员钻石提成记录
     * @param spreadDto
     * @return
     */
    @RequestMapping(value = "/record/spreadDiamond/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listRecordSpreadDiamondAddByQry(@RequestBody SpreadDto spreadDto) {
        return spreadService.listRecordDaySpreadDiamondByQry(spreadDto);
    }

    /**
     * 赠送金币
     * @param spreadDto
     * @return
     */
    @RequestMapping(value = "/giveGold", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO giveGold(@RequestBody SpreadDto spreadDto) {
        return spreadService.giveGold(spreadDto);
    }

    /**
     * 赠送钻石
     * @param spreadDto
     * @return
     */
    @RequestMapping(value = "/giveDiamond", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO giveDiamond(@RequestBody SpreadDto spreadDto) {
        return spreadService.giveDiamond(spreadDto);
    }

    /**
     * 查询按日汇总提成记录列表(金币)
     * @param spreadDto
     * @return
     */
    @RequestMapping(value = "/gold/daily/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listDailyCountSpreadGoldRecordByQry(@RequestBody SpreadDto spreadDto) {
        return new ResponseBO(spreadService.listDailyCountSpreadGoldRecordByQry(spreadDto));
    }

    /**
     * 查询按日汇总提成记录列表(钻石)
     * @param spreadDto
     * @return
     */
    @RequestMapping(value = "/diamond/daily/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listDailyCountSpreadDiamondRecordByQry(@RequestBody SpreadDto spreadDto) {
        return new ResponseBO(spreadService.listDailyCountSpreadDiamondRecordByQry(spreadDto));
    }
}
