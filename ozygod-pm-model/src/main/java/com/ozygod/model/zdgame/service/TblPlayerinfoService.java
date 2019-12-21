package com.ozygod.model.zdgame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdgame.entity.TblPlayerinfoEntity;
import com.ozygod.model.zdgame.dto.TblPlayerinfoListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 用户数据表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 11:39:15
 */
public interface TblPlayerinfoService extends IService<TblPlayerinfoEntity> {

    ResponseBO queryPage(TblPlayerinfoListDto tblPlayerinfo);

}

