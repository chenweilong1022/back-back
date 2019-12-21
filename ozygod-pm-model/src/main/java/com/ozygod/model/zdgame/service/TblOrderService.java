package com.ozygod.model.zdgame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.dto.TblOrderListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 14:18:44
 */
public interface TblOrderService extends IService<TblOrderEntity> {

    ResponseBO queryPage(TblOrderListDto tblOrder);

}

