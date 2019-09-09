package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdmanage.bo.ShareImageBO;
import com.ozygod.model.zdmanage.entity.ShareImageEntity;

import java.util.List;

public interface ShareImageEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShareImageEntity record);

    int insertSelective(ShareImageEntity record);

    ShareImageEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareImageEntity record);

    int updateByPrimaryKey(ShareImageEntity record);

    /**
     * 查询分享图列表
     * @param dto
     * @return
     */
    List<ShareImageBO> listShareImageByQry(PlatformDto dto);

    /**
     * 查询分享图总数
     * @param dto
     * @return
     */
    int countShareImageByQry(PlatformDto dto);
}