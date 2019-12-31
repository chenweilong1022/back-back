package com.ozygod.model.zdlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdlog.entity.TblRecordChannelDailyEntity;
import com.ozygod.model.zdlog.dto.TblRecordChannelDailyListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 渠道日报
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-31 11:11:19
 */
public interface TblRecordChannelDailyService extends IService<TblRecordChannelDailyEntity> {

    ResponseBO queryPage(TblRecordChannelDailyListDto tblRecordChannelDaily);

}

