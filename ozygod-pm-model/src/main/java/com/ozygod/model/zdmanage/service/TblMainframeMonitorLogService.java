package com.ozygod.model.zdmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdmanage.entity.TblMainframeMonitorLogEntity;
import com.ozygod.model.zdmanage.dto.TblMainframeMonitorLogListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 所有监控的日志主机
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-08 16:28:15
 */
public interface TblMainframeMonitorLogService extends IService<TblMainframeMonitorLogEntity> {

    ResponseBO queryPage(TblMainframeMonitorLogListDto tblMainframeMonitorLog);

}

