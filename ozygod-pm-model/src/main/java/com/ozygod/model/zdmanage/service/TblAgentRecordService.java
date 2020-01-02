package com.ozygod.model.zdmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdmanage.entity.TblAgentRecordEntity;
import com.ozygod.model.zdmanage.dto.TblAgentRecordListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 14:17:46
 */
public interface TblAgentRecordService extends IService<TblAgentRecordEntity> {

    ResponseBO queryPage(TblAgentRecordListDto tblAgentRecord);

}

