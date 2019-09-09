package com.ozygod.model.zdlog.dao;

import com.ozygod.model.zdlog.bo.BankGoldBO;
import com.ozygod.model.zdlog.dto.BankGoldDto;
import com.ozygod.model.zdlog.entity.BankGoldEntity;

import java.util.List;

public interface BankGoldEntityMapper {
    int insert(BankGoldEntity record);

    int insertSelective(BankGoldEntity record);

    /**
     * 查询玩家银行日志列表
     * @param dto
     * @return
     */
    List<BankGoldBO> listPlayerBankGoldByQry(BankGoldDto dto);

    /**
     * 查询玩家银行日志总数
     * @param dto
     * @return
     */
    int totalCountPlayerBankGoldByQry(BankGoldDto dto);

    /**
     * 根据指定条件统计银行金币
     * @param dto
     * @return
     */
    long countBankGoldByQry(BankGoldDto dto);
}