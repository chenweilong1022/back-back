package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.entity.TblOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-19 14:18:44
 */
@Mapper
public interface TblOrderDao extends BaseMapper<TblOrderEntity> {

    Integer rechargePrice(@Param("userid")Long userid);
}
