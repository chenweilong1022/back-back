package com.ozygod.model.zdlog.dao;

import com.ozygod.model.zdlog.entity.RecordPlatformSubsidyEntity;
import java.util.Date;

public interface RecordPlatformSubsidyEntityMapper {
    int deleteByPrimaryKey(Date recordTime);

    int insert(RecordPlatformSubsidyEntity record);

    int insertSelective(RecordPlatformSubsidyEntity record);

    RecordPlatformSubsidyEntity selectByPrimaryKey(Date recordTime);

    int updateByPrimaryKeySelective(RecordPlatformSubsidyEntity record);

    int updateByPrimaryKey(RecordPlatformSubsidyEntity record);
}