package com.ozygod.model.zdmanage.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdmanage.entity.TblWithdrawOrderEntity;
import com.ozygod.model.zdmanage.dto.TblWithdrawOrderListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.List;
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


    /**
     * 根据时间查询中间成功提现的列表
     * @param begin
     * @param end
     * @return
     */
    List<TblWithdrawOrderEntity> haveWithdrawal(DateTime begin,DateTime end,List<Long> userIds);
    /**
     * 根据list计算总提现
     * @param tblWithdrawOrderEntitys
     * @return
     */
    int totalBack(List<TblWithdrawOrderEntity> tblWithdrawOrderEntitys);
    /**
     * 根据时间用户id计算总提现
     * @param begin
     * @param end
     * @param userIds
     * @return
     */
    int totalBack(DateTime begin,DateTime end,List<Long> userIds);
    /**
     * 根据list计算总提现人数
     * @param tblWithdrawOrderEntitys
     * @return
     */
    int totalBackNumber(List<TblWithdrawOrderEntity> tblWithdrawOrderEntitys);


}

