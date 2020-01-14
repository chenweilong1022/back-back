package com.ozygod.model.zdmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdmanage.entity.TblSysLogEntity;
import com.ozygod.model.zdmanage.dto.TblSysLogListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 系统日志
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-14 14:35:49
 */
public interface TblSysLogService extends IService<TblSysLogEntity> {

    ResponseBO queryPage(TblSysLogListDto tblSysLog);

}

