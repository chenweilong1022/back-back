package com.ozygod.model.zdspread.dao;

import com.ozygod.model.zdspread.bo.SpreadUserBO;
import com.ozygod.model.zdspread.dto.SpreadUserDto;
import com.ozygod.model.zdspread.entity.SpreadUserEntity;

import java.util.List;

public interface SpreadUserEntityMapper {
    int insert(SpreadUserEntity record);

    int insertSelective(SpreadUserEntity record);

    /**
     * 新增推广员存储过程
     * @param bo
     * @return
     */
    int addSpreadUserProc(SpreadUserBO bo);

    /**
     * 新增推广员
     * @param bo
     * @return
     */
    int addSpreadUser(SpreadUserBO bo);

    /**
     * 查询推广用户列表
     * @return
     */
    List<SpreadUserBO> listSpreadUserByQry(SpreadUserDto dto);

    /**
     * 查询推广用户列表总数
     * @param dto
     * @return
     */
    int totalSpreadUserByQry(SpreadUserDto dto);

    /**
     * 推广员登录
     * @param dto
     * @return
     */
    SpreadUserBO spreadUserlogin(SpreadUserDto dto);

    /**
     * 查询推广员详情
     * @param dto
     * @return
     */
    SpreadUserBO getSpreadUserByQry(SpreadUserDto dto);

    /**
     * 修改密码
     * @param bo
     * @return
     */
    int changePassword(SpreadUserBO bo);

    /**
     * 重置密码
     * @param bo
     * @return
     */
    int resetPassword(SpreadUserBO bo);

    /**
     * 取消推广员
     * @param spreadUserId
     * @return
     */
    int cancelSpreadUserById(Integer spreadUserId);

    /**
     * 重置推广员提成比率
     * @param bo
     * @return
     */
    int resetSpreadUserRatio(SpreadUserBO bo);

    /**
     * 调整推广员钻石
     * @param bo
     * @return
     */
    int changeSpreadUserDiamond(SpreadUserBO bo);

    /**
     * 修改推广员信息
     * @param bo
     * @return
     */
    int updateSpreadUser(SpreadUserBO bo);
}
