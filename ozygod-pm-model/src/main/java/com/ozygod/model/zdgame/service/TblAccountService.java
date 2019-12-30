package com.ozygod.model.zdgame.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ozygod.model.zdgame.entity.TblAccountEntity;
import com.ozygod.model.zdgame.dto.TblAccountListDto;
import com.ozygod.base.bo.ResponseBO;

import java.util.List;
import java.util.Map;

/**
 * 用户账号表
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-16 15:29:37
 */
public interface TblAccountService extends IService<TblAccountEntity> {

    ResponseBO queryPage(TblAccountListDto tblAccount);

    /**
     * 在线用户数
     * @return
     */
    int onlineNumber();
    /**
     * 登录数
     * @return
     */
    int loginNumber(DateTime begin, DateTime end, DateTime oldTime,List<Long> userIds);

}

