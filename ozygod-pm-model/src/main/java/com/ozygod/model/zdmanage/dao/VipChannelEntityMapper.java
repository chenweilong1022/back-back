package com.ozygod.model.zdmanage.dao;

import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdmanage.bo.VipChannelBO;
import com.ozygod.model.zdmanage.entity.VipChannelEntity;

import java.util.List;

public interface VipChannelEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VipChannelEntity record);

    int insertSelective(VipChannelEntity record);

    VipChannelEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VipChannelEntity record);

    int updateByPrimaryKey(VipChannelEntity record);

    /**
     * 查询VIP充值通道列表
     * @param dto
     * @return
     */
    List<VipChannelBO> listVipChannelByQry(PlatformDto dto);

    /**
     * 查询VIP充值通道总数
     * @param dto
     * @return
     */
    int totalVipChannelByQry(PlatformDto dto);
}