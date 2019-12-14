package com.ozygod.model.zdconfig.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ozygod.model.zdconfig.dao.TblIpWhiteListDao;
import com.ozygod.model.zdconfig.entity.TblIpWhiteListEntity;
import com.ozygod.model.zdconfig.service.TblIpWhiteListService;


@Service("tblIpWhiteListService")
public class TblIpWhiteListServiceImpl extends ServiceImpl<TblIpWhiteListDao, TblIpWhiteListEntity> implements TblIpWhiteListService {


}
