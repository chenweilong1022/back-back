package com.ozygod.player.service.impl;

import com.ozygod.base.service.IGamePushService;
import com.ozygod.base.utils.CommonUtil;
import com.ozygod.model.zdmanage.bo.BlackWhiteListBO;
import com.ozygod.model.zdmanage.dao.BlackWhiteListEntityMapper;
import com.ozygod.model.zdmanage.dto.BlackWhiteListDto;
import com.ozygod.model.zdmanage.entity.BlackWhiteListEntity;
import com.ozygod.player.service.IBlackWhiteListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/13
 */
@Component
@Slf4j
public class BlackWhiteListServiceImpl implements IBlackWhiteListService {

    @Autowired
    private BlackWhiteListEntityMapper blackWhiteListEntityMapper;

    @Autowired
    private IGamePushService gamePushService;

    /**
     * 查询是否在列表中已存在
     *
     * @param dto
     * @return
     */
    @Override
    public int existInList(BlackWhiteListDto dto) {
        return blackWhiteListEntityMapper.existInList(dto);
    }

    /**
     * 查询黑白名单列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<BlackWhiteListBO> listBlackWhiteListByQry(BlackWhiteListDto dto) {
        return blackWhiteListEntityMapper.listBlackWhiteListByQry(dto);
    }

    /**
     * 查询黑白名单列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalBlackWhiteListByQry(BlackWhiteListDto dto) {
        return blackWhiteListEntityMapper.totalBlackWhiteListByQry(dto);
    }

    /**
     * 加入黑白名单
     *
     * @param bo
     * @return
     */
    @Override
    @Transactional("zdmanageTransactionManager")
    public int saveBlackWhiteListBO(BlackWhiteListBO bo) {
        bo.setAddTime(new Date());
        bo.setDeleted(0);

        int result = blackWhiteListEntityMapper.insertSelective(bo);

        if (result > 0) {
            this.notifyGameRuleChange();
        }

        return result;
    }

    /**
     * 通知游戏黑白名单变更
     */
    private void notifyGameRuleChange() {
        //黑名单
        List<BlackWhiteListEntity> blackList = blackWhiteListEntityMapper.listTargetTypeBlackWhiteListByQry(1);
        String blackListStr = "";
        if (!CommonUtil.isEmpty(blackList)) {

            for (BlackWhiteListEntity entity :
                    blackList) {
                blackListStr += entity.getPlayerId() + ",";
            }
        }

        gamePushService.notifyGameBlackAndWhiteList(1, blackListStr);

        //白名单
        List<BlackWhiteListEntity> whiteList = blackWhiteListEntityMapper.listTargetTypeBlackWhiteListByQry(2);
        String whiteListStr = "";
        if (!CommonUtil.isEmpty(whiteList)) {
            for (BlackWhiteListEntity entity :
                    whiteList) {
                whiteListStr += entity.getPlayerId() + ",";
            }
        }

        gamePushService.notifyGameBlackAndWhiteList(2, whiteListStr);


    }

    /**
     * 修改列表数据
     *
     * @param bo
     * @return
     */
    @Override
    @Transactional("zdmanageTransactionManager")
    public int updateBlackWhiteListBO(BlackWhiteListBO bo) {
        int result = blackWhiteListEntityMapper.updateByPrimaryKeySelective(bo);
        if (result > 0) {
            this.notifyGameRuleChange();
        }
        return result;
    }
}
