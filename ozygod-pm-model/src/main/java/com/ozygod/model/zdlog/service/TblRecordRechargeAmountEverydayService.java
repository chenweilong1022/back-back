package com.ozygod.model.zdlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdlog.entity.TblRecordRechargeAmountEverydayEntity;
import com.ozygod.model.zdlog.dto.TblRecordRechargeAmountEverydayListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 每日充值记录表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 15:27:02
 */
public interface TblRecordRechargeAmountEverydayService extends IService<TblRecordRechargeAmountEverydayEntity> {

    ResponseBO queryPage(TblRecordRechargeAmountEverydayListDto tblRecordRechargeAmountEveryday);

}

