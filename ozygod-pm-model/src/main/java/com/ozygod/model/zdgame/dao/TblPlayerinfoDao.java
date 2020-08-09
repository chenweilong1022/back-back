package com.ozygod.model.zdgame.dao;

import com.ozygod.model.zdgame.entity.TblPlayerinfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ozygod.model.zdgame.vo.AgentTreeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数据表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-20 11:39:15
 */
@Mapper
public interface TblPlayerinfoDao extends BaseMapper<TblPlayerinfoEntity> {


    List<AgentTreeVo> agentTree(@Param("saler") Long saler,@Param("userid") Long userid);

}
