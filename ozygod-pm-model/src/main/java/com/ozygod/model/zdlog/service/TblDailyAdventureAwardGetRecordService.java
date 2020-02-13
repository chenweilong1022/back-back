package com.ozygod.model.zdlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdlog.entity.TblDailyAdventureAwardGetRecordEntity;
import com.ozygod.model.zdlog.dto.TblDailyAdventureAwardGetRecordListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 每日闯关记录领取表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-12 17:05:11
 */
public interface TblDailyAdventureAwardGetRecordService extends IService<TblDailyAdventureAwardGetRecordEntity> {

    ResponseBO queryPage(TblDailyAdventureAwardGetRecordListDto tblDailyAdventureAwardGetRecord);

}

