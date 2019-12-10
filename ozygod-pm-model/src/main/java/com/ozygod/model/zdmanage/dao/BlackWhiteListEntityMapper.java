package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.zdmanage.bo.BlackWhiteListBO;
import com.ozygod.model.zdmanage.dto.BlackWhiteListDto;
import com.ozygod.model.zdmanage.entity.BlackWhiteListEntity;

import java.util.List;

public interface BlackWhiteListEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlackWhiteListEntity record);

    int insertSelective(BlackWhiteListEntity record);

    BlackWhiteListEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlackWhiteListEntity record);

    int updateByPrimaryKey(BlackWhiteListEntity record);

    /**
     * 是否在列表中存在
     * @param dto
     * @return
     */
    int existInList(BlackWhiteListDto dto);

    /**
     * 查询指定类型的黑白名单列表
     * @param listType
     * @return
     */
    List<BlackWhiteListEntity> listTargetTypeBlackWhiteListByQry(Integer listType);

    /**
     * 查询黑白名单列表
     * @param dto
     * @return
     */
    List<BlackWhiteListBO> listBlackWhiteListByQry(BlackWhiteListDto dto);

    /**
     * 查询黑白名单列表总数
     * @param dto
     * @return
     */
    int totalBlackWhiteListByQry(BlackWhiteListDto dto);
}