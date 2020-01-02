package com.ozygod.model.zdmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdmanage.entity.TblAgentRealtimeEntity;
import com.ozygod.model.zdmanage.dto.TblAgentRealtimeListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-02 14:17:45
 */
public interface TblAgentRealtimeService extends IService<TblAgentRealtimeEntity> {

    ResponseBO queryPage(TblAgentRealtimeListDto tblAgentRealtime);

}

