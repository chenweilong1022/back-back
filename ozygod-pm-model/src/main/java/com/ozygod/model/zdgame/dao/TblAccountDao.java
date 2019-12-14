package com.ozygod.model.zdgame.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户账号表
 * 
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-13 17:20:00
 */
@Mapper
public interface TblAccountDao extends BaseMapper<TblAccountEntity> {
	
}
