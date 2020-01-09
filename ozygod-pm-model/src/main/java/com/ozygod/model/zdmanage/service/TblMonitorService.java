package com.ozygod.model.zdmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdmanage.entity.TblMonitorEntity;
import com.ozygod.model.zdmanage.dto.TblMonitorListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 监听器表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-08 16:28:15
 */
public interface TblMonitorService extends IService<TblMonitorEntity> {

    ResponseBO queryPage(TblMonitorListDto tblMonitor);

}

