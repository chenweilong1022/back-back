package com.ozygod.model.zdlog.dao;

import com.ozygod.model.common.bo.FinancialStatementBO;
import com.ozygod.model.zdlog.bo.RemitGoldRecordBO;
import com.ozygod.model.zdlog.dto.PlayerLogDto;
import com.ozygod.model.zdlog.entity.ManagerRemitGoldRecordEntity;
import com.ozygod.model.zdmanage.bo.ManagerBO;

import java.util.List;

public interface ManagerRemitGoldRecordEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerRemitGoldRecordEntity record);

    int insertSelective(ManagerRemitGoldRecordEntity record);

    ManagerRemitGoldRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerRemitGoldRecordEntity record);

    int updateByPrimaryKey(ManagerRemitGoldRecordEntity record);

    /**
     * 查询划账日志列表
     * @param dto
     * @return
     */
    List<RemitGoldRecordBO> listRemitGoldRecordByQry(PlayerLogDto dto);

    /**
     * 查询划账日志列表总数
     * @param dto
     * @return
     */
    int totalRemitGoldRecordByQry(PlayerLogDto dto);

    /**
     * 获取划账总额
     * @param dto
     * @return
     */
    long getTotalRemitGoldByQry(PlayerLogDto dto);

    /**
     * 查询手工划账对账记录
     * @param dto
     * @return
     */
    List<FinancialStatementBO> listManualRechargeStatementByQry(PlayerLogDto dto);

    /**
     * 获取人工充值的管理员列表
     * @param dto
     * @return
     */
    List<ManagerBO> listRechargeManagerByQry(PlayerLogDto dto);

    /**
     * 统计人工充值的金币数量
     * @param dto
     * @return
     */
    long countRechargeGoldByQry(PlayerLogDto dto);
}