package com.ozygod.model.zdlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdlog.entity.TblRecordAccountLoginRegisterEverydayEntity;
import com.ozygod.model.zdlog.dto.TblRecordAccountLoginRegisterEverydayListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 用户每日登录记录
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-18 18:16:55
 */
public interface TblRecordAccountLoginRegisterEverydayService extends IService<TblRecordAccountLoginRegisterEverydayEntity> {

    ResponseBO queryPage(TblRecordAccountLoginRegisterEverydayListDto tblRecordAccountLoginRegisterEveryday);

}

