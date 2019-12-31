package com.ozygod.model.zdlog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdlog.entity.TblGameRecordEntity;
import com.ozygod.model.zdlog.dto.TblGameRecordListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-31 14:29:10
 */
public interface TblGameRecordService extends IService<TblGameRecordEntity> {

    ResponseBO queryPage(TblGameRecordListDto tblGameRecord);

}

