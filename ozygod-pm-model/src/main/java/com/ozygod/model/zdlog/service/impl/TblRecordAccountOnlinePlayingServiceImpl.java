package com.ozygod.model.zdlog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.model.zdlog.dao.TblRecordAccountOnlinePlayingDao;
import com.ozygod.model.zdlog.entity.TblRecordAccountOnlinePlayingEntity;
import com.ozygod.model.zdlog.service.TblRecordAccountOnlinePlayingService;
import org.springframework.stereotype.Service;


@Service("tblRecordAccountOnlinePlayingService")
public class TblRecordAccountOnlinePlayingServiceImpl extends ServiceImpl<TblRecordAccountOnlinePlayingDao, TblRecordAccountOnlinePlayingEntity> implements TblRecordAccountOnlinePlayingService {

//    @Override
//    public PageUtils queryPage(TblRecordAccountOnlinePlayingEntity tblRecordAccountOnlinePlaying) {
//        IPage<TblRecordAccountOnlinePlayingEntity> page = baseMapper.selectPage(
//                new Query<TblRecordAccountOnlinePlayingEntity>(tblRecordAccountOnlinePlaying).getPage(),
//                new QueryWrapper<TblRecordAccountOnlinePlayingEntity>()
//        );
//
//        return new PageUtils(page);
//    }

}
