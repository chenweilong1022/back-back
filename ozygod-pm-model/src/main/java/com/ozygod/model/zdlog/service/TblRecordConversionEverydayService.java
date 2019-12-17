package com.ozygod.model.zdlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdlog.entity.TblRecordConversionEverydayEntity;
import com.ozygod.model.zdlog.dto.TblRecordConversionEverydayListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 每日兑换记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-17 09:59:51
 */
public interface TblRecordConversionEverydayService extends IService<TblRecordConversionEverydayEntity> {

    ResponseBO queryPage(TblRecordConversionEverydayListDto tblRecordConversionEveryday);

}

