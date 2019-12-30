package com.ozygod.model.zdgame.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.dto.TblOrderListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.List;
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

    /**
     * 订单数量
     * @param begin
     * @param end
     * @param payTime
     * @return
     */
    int orderCount(DateTime begin,DateTime end,DateTime payTime);

    /**
     * 充值金额
     * @return
     */
    int recharge(DateTime begin, DateTime end, List<Long> userIds);

}

