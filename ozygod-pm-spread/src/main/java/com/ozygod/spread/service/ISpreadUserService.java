package com.ozygod.spread.service;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdspread.bo.SpreadUserBO;
import com.ozygod.model.zdspread.dto.SpreadUserDto;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/22
 */
public interface ISpreadUserService {

    /**
     * 新增推广员
     * @param bo
     * @return
     */
    int addSpreadUser(SpreadUserBO bo);

    /**
     * 推广用户登录
     * @param dto
     * @return
     */
    ResponseBO spreadUserLogin(SpreadUserDto dto);

    /**
     * 修改密码
     * @param bo
     * @return
     */
    ResponseBO changePassword(SpreadUserBO bo);

    /**
     * 退出登录
     * @param token
     * @param spreadUserId
     * @return
     */
    void logout(String token,Integer spreadUserId);

    /**
     * 查询推广用户详情
     * @param dto
     * @return
     */
    ResponseBO getSpreadUserByQry(SpreadUserDto dto);

    /**
     * 查询推广用户列表
     * @param dto
     * @return
     */
    ResponseBO listSpreadUserByQry(SpreadUserDto dto);

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
