package com.ozygod.model.zdconfig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdconfig.entity.TblActiveConfigEntity;
import com.ozygod.model.zdconfig.dto.TblActiveConfigListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 活动配置表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-13 11:20:04
 */
public interface TblActiveConfigService extends IService<TblActiveConfigEntity> {

    ResponseBO queryPage(TblActiveConfigListDto tblActiveConfig);

    @Override
    boolean updateById(TblActiveConfigEntity entity);
}

