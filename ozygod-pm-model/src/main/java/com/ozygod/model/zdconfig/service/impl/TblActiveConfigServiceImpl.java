package com.ozygod.model.zdconfig.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ozygod.base.enums.GameGoldReason;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ozygod.base.bo.ResponseBO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.ozygod.model.zdconfig.dao.TblActiveConfigDao;
import com.ozygod.model.zdconfig.entity.TblActiveConfigEntity;
import com.ozygod.model.zdconfig.dto.TblActiveConfigListDto;
import com.ozygod.model.zdconfig.service.TblActiveConfigService;


@Service("tblActiveConfigService")
public class TblActiveConfigServiceImpl extends ServiceImpl<TblActiveConfigDao, TblActiveConfigEntity> implements TblActiveConfigService {


    @Override
    public ResponseBO queryPage(TblActiveConfigListDto tblActiveConfig) {
        IPage<TblActiveConfigEntity> page = baseMapper.selectPage(
                tblActiveConfig.getPage(),
                new QueryWrapper<TblActiveConfigEntity>()
        );

        if (CollUtil.isEmpty(page.getRecords())) {

            Page page1 = tblActiveConfig.getPage();

            GameGoldReason[] values = GameGoldReason.values();

            List<TblActiveConfigEntity> tblActiveConfigEntities = new ArrayList<>();
            for (GameGoldReason value : values) {
                TblActiveConfigEntity tblActiveConfigEntity = new TblActiveConfigEntity();
                tblActiveConfigEntity.setActiveConfigId(value.getKey());
                tblActiveConfigEntity.setActive(value.getValue());
                tblActiveConfigEntity.setEnabled(value.getEnabled());
                tblActiveConfigEntities.add(tblActiveConfigEntity);
            }

            page1.setTotal(tblActiveConfigEntities.size());
            page1.setRecords(tblActiveConfigEntities);

            return ResponseBO.page(page1);
        }

        return ResponseBO.page(page);
    }


    @Override
    public boolean updateById(TblActiveConfigEntity entity) {
        TblActiveConfigEntity one = this.getOne(new QueryWrapper<TblActiveConfigEntity>().lambda()
                .eq(TblActiveConfigEntity::getActiveConfigId, entity.getActiveConfigId())
        );
        if (ObjectUtil.isNotNull(one)) {
            entity.setId(one.getId());
            return baseMapper.updateById(entity) > 0;
        }
        return this.save(entity);
    }

    @Override
    public TblActiveConfigEntity getById(Serializable id) {

        TblActiveConfigEntity tblActiveConfigEntity = this.getOne(new QueryWrapper<TblActiveConfigEntity>().lambda().eq(TblActiveConfigEntity::getActiveConfigId,id));
        if (ObjectUtil.isNull(tblActiveConfigEntity)) {
            GameGoldReason gameGoldReason = GameGoldReason.getByKey(Integer.valueOf(String.valueOf(id)));
            tblActiveConfigEntity = new TblActiveConfigEntity();
            tblActiveConfigEntity.setActiveConfigId(gameGoldReason.getKey());
            tblActiveConfigEntity.setActive(gameGoldReason.getValue());
            tblActiveConfigEntity.setEnabled(gameGoldReason.getEnabled());
        }
        return tblActiveConfigEntity ;
    }
}
