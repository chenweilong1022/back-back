package com.ozygod.model.zdgame.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.ozygod.model.zdgame.dto.TblOrderListDto;
import com.ozygod.base.bo.ResponseBO;
import org.apache.ibatis.annotations.Param;

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
    /**
     * 充值金额
     * @return
     */
    int recharge(List<TblOrderEntity> tblOrderEntities);


    /**
     * 充值用户数量
     * @return
     */
    int rechargeUserCount(DateTime begin, DateTime end, List<Long> userIds);
    /**
     * 充值用户数量
     * @return
     */
    int rechargeUserCount(List<TblOrderEntity> tblOrderEntities);

    /**
     * 充值订单数量
     * @return
     */
    int rechargeOrderCount(DateTime begin, DateTime end, List<Long> userIds);
    /**
     * 充值订单数量
     * @return
     */
    int rechargeOrderCount(List<TblOrderEntity> tblOrderEntities);
    /**
     * 充值订单列表
     * @return
     */
    List<TblOrderEntity> rechargeOrderList(DateTime begin, DateTime end, List<Long> userIds);

    Integer rechargePrice(Long userid);

}

