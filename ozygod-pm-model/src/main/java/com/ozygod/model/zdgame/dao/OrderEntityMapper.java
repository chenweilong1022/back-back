package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.bo.PlayerOrderBO;
import com.ozygod.model.zdgame.dto.PlayerOrderDto;
import com.ozygod.model.zdgame.entity.OrderEntity;

import java.util.List;

public interface OrderEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderEntity record);

    int insertSelective(OrderEntity record);

    OrderEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderEntity record);

    int updateByPrimaryKey(OrderEntity record);

    /**
     * 查询玩家订单列表
     * @param dto
     * @return
     */
    List<PlayerOrderBO> listPlayerOrderByQry(PlayerOrderDto dto);

    /**
     * 查询玩家订单总数
     * @param dto
     * @return
     */
    int totalCountPlayerOrderByQry(PlayerOrderDto dto);

    /**
     * 查询充值总额
     * @param dto
     * @return
     */
    long totalRechargeAmountByQry(PlayerOrderDto dto);
}