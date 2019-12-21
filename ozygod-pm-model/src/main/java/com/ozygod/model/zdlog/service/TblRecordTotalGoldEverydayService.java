package com.ozygod.model.zdlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdlog.entity.TblRecordTotalGoldEverydayEntity;
import com.ozygod.model.zdlog.dto.TblRecordTotalGoldEverydayListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 每日总金币统计表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 11:38:17
 */
public interface TblRecordTotalGoldEverydayService extends IService<TblRecordTotalGoldEverydayEntity> {

    ResponseBO queryPage(TblRecordTotalGoldEverydayListDto tblRecordTotalGoldEveryday);

}

