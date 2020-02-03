package com.ozygod.model.zdconfig.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdconfig.dao.TblGameRoomDao;
import com.ozygod.model.zdconfig.entity.TblGameRoomEntity;
import com.ozygod.model.zdconfig.dto.TblGameRoomListDto;
import com.ozygod.model.zdconfig.service.TblGameRoomService;


@Service("tblGameRoomService")
public class TblGameRoomServiceImpl extends ServiceImpl<TblGameRoomDao, TblGameRoomEntity> implements TblGameRoomService {


    @Override
    public ResponseBO queryPage(TblGameRoomListDto tblGameRoom) {
        IPage<TblGameRoomEntity> page = baseMapper.selectPage(
                tblGameRoom.getPage(),
                new QueryWrapper<TblGameRoomEntity>()
        );
        return ResponseBO.page(page);
    }
}
