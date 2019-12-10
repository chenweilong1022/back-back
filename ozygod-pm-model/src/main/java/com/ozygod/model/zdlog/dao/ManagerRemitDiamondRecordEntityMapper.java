package com.ozygod.model.zdlog.dao;

import com.ozygod.model.zdlog.bo.RemitDiamondRecordBO;
import com.ozygod.model.zdlog.dto.PlayerLogDto;
import com.ozygod.model.zdlog.entity.ManagerRemitDiamondRecordEntity;

import java.util.List;

public interface ManagerRemitDiamondRecordEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerRemitDiamondRecordEntity record);

    int insertSelective(ManagerRemitDiamondRecordEntity record);

    ManagerRemitDiamondRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerRemitDiamondRecordEntity record);

    int updateByPrimaryKey(ManagerRemitDiamondRecordEntity record);

    /**
     * 查询划账日志列表
     * @param dto
     * @return
     */
    List<RemitDiamondRecordBO> listRemitDiamondRecordByQry(PlayerLogDto dto);

    /**
     * 查询划账日志列表总数
     * @param dto
     * @return
     */
    int totalRemitDiamondRecordByQry(PlayerLogDto dto);

    /**
     * 获取划账总额
     * @param dto
     * @return
     */
    int getTotalRemitDiamondByQry(PlayerLogDto dto);
}