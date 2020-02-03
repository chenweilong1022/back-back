package com.ozygod.model.zdconfig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdconfig.entity.TblGameEntity;
import com.ozygod.model.zdconfig.dto.TblGameListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-03 10:27:36
 */
public interface TblGameService extends IService<TblGameEntity> {

    ResponseBO queryPage(TblGameListDto tblGame);

}

