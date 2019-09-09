package com.ozygod.player.service;

import com.ozygod.model.zdmanage.bo.BlackWhiteListBO;
import com.ozygod.model.zdmanage.dto.BlackWhiteListDto;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/13
 */
public interface IBlackWhiteListService {

    /**
     * 查询是否在列表中已存在
     * @param dto
     * @return
     */
    int existInList(BlackWhiteListDto dto);

    /**
     * 查询黑白名单列表
     * @param dto
     * @return
     */
    List<BlackWhiteListBO> listBlackWhiteListByQry(BlackWhiteListDto dto);

    /**
     * 查询黑白名单列表总数
     * @param dto
     * @return
     */
    int totalBlackWhiteListByQry(BlackWhiteListDto dto);

    /**
     * 加入黑白名单
     * @param bo
     * @return
     */
    int saveBlackWhiteListBO(BlackWhiteListBO bo);

    /**
     * 修改列表数据
     * @param bo
     * @return
     */
    int updateBlackWhiteListBO(BlackWhiteListBO bo);
}
