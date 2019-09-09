package com.ozygod.model.zdspread.dao;

import com.ozygod.model.zdspread.entity.RecordExchangeDiamondEntity;

public interface RecordExchangeDiamondEntityMapper {
    int insert(RecordExchangeDiamondEntity record);

    int insertSelective(RecordExchangeDiamondEntity record);
}