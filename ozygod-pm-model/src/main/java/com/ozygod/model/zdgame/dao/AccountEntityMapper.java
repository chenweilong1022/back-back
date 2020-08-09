package com.ozygod.model.zdgame.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ozygod.model.zdgame.bo.PlayerAccountBO;
import com.ozygod.model.zdgame.dto.PlayerAccountDto;
import com.ozygod.model.zdgame.entity.AccountEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountEntityMapper {
    int deleteByPrimaryKey(Long userid);

    int insert(AccountEntity record);

    int insertSelective(AccountEntity record);

    AccountEntity selectByPrimaryKey(Long userid);

    int updateByPrimaryKeySelective(AccountEntity record);

    int updateByPrimaryKey(AccountEntity record);

    PlayerAccountBO getPlayerAccountById(Integer playerId);

    /**
     * 查询玩家详情
     * @param showId
     * @return
     */
    PlayerAccountBO getPlayerAccountByShowId(Integer showId);

    /**
     * 查询玩家帐号列表
     * @param dto
     * @return
     */
    List<PlayerAccountBO> listPlayerAccountByQry(PlayerAccountDto dto);

    /**
     * 查询玩家帐号总数
     * @param dto
     * @return
     */
    int totalCountPlayerAccountByQry(PlayerAccountDto dto);




    /**
     * 查询在线玩家列表
     * @param dto
     * @return
     */
    List<PlayerAccountBO> listOnlinePlayerQry(PlayerAccountDto dto);
    /**
     * 查询在线玩家分页
     * @param page
     * @param dto
     * @return
     */
    List<PlayerAccountBO> listOnlinePlayerQryPage(IPage page, @Param(Constants.WRAPPER)PlayerAccountDto dto,@Param("realUserId") Integer realUserId);

    /**
     * 查询在线玩家总金币
     * @param page
     * @param dto
     * @return
     */
    PlayerAccountBO listOnlinePlayerTotalGold(IPage page, @Param(Constants.WRAPPER)PlayerAccountDto dto,@Param("realUserId") Integer realUserId);


    /**
     * 查询在线玩家列表总数
     * @param dto
     * @return
     */
    int totalOnlinePlayerByQry(PlayerAccountDto dto);
}
