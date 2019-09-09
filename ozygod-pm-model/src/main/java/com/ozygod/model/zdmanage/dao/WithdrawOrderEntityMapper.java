package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.common.bo.FinancialStatementBO;
import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdmanage.bo.WithdrawOrderBO;
import com.ozygod.model.zdmanage.dto.BusinessDto;
import com.ozygod.model.zdmanage.entity.WithdrawOrderEntity;

import java.util.List;

public interface WithdrawOrderEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WithdrawOrderEntity record);

    int insertSelective(WithdrawOrderEntity record);

    WithdrawOrderEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WithdrawOrderEntity record);

    int updateByPrimaryKey(WithdrawOrderEntity record);

    /**
     * 查询玩家提现列表
     * @param dto
     * @return
     */
    List<WithdrawOrderBO> listWithdrawOrderByQry(BusinessDto dto);

    /**
     * 查询玩家提现总数
     * @param dto
     * @return
     */
    int totalWithdrawOrderbyQry(BusinessDto dto);

    /**
     * 查询提现对账单
     * @param dto
     * @return
     */
    List<FinancialStatementBO> listWithdrawStatementByQry(PlatformDto dto);

    /**
     * 统计提现金额
     * @param dto
     * @return
     */
    long countWithdrawOrderByQry(BusinessDto dto);
}