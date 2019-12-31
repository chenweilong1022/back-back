package com.ozygod.model.zdmanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdmanage.entity.TblWithdrawOrderEntity;
import com.ozygod.model.zdmanage.dto.TblWithdrawOrderListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.Map;

/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-30 18:00:03
 */
public interface TblWithdrawOrderService extends IService<TblWithdrawOrderEntity> {

    ResponseBO queryPage(TblWithdrawOrderListDto tblWithdrawOrder);

}

