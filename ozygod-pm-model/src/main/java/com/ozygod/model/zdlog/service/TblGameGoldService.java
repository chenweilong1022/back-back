package com.ozygod.model.zdlog.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdlog.entity.TblGameGoldEntity;
import com.ozygod.model.zdlog.dto.TblGameGoldListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.List;
import java.util.Map;

/**
 * 金币日志
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-31 13:48:42
 */
public interface TblGameGoldService extends IService<TblGameGoldEntity> {

    ResponseBO queryPage(TblGameGoldListDto tblGameGold);

    List<TblGameGoldEntity> tblGameGoldEntities(DateTime begin,DateTime end,List<Long> userIds);

    long totalRevenue(List<TblGameGoldEntity> tblGameGoldEntities);
}

