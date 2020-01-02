package com.ozygod.model.zdlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdlog.entity.TblRecordChannelGeneralizeEntity;
import com.ozygod.model.zdlog.dto.TblRecordChannelGeneralizeListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 每日渠道推广统计
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 13:54:15
 */
public interface TblRecordChannelGeneralizeService extends IService<TblRecordChannelGeneralizeEntity> {

    ResponseBO queryPage(TblRecordChannelGeneralizeListDto tblRecordChannelGeneralize);

}

