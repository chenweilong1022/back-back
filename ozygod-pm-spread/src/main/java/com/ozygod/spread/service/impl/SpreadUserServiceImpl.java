package com.ozygod.spread.service.impl;

import com.ozygod.base.auth.AccessToken;
import com.ozygod.base.auth.service.AuthService;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.redis.auth.AccessTokenRedisDao;
import com.ozygod.base.utils.CommonUtil;
import com.ozygod.base.utils.MD5Util;
import com.ozygod.model.zdmanage.dao.ManageLogEntityMapper;
import com.ozygod.model.zdmanage.entity.ManageLogEntity;
import com.ozygod.model.zdspread.bo.SpreadUserBO;
import com.ozygod.model.zdspread.dao.SpreadUserEntityMapper;
import com.ozygod.model.zdspread.dto.SpreadUserDto;
import com.ozygod.spread.service.ISpreadUserService;
import com.ozygod.spread.utils.SpreadConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/22
 */
@Component
public class SpreadUserServiceImpl implements ISpreadUserService {

    @Autowired
    private SpreadUserEntityMapper spreadUserEntityMapper;
    @Autowired
    private AuthService authService;
    @Autowired
    private AccessTokenRedisDao accessTokenRedisDao;
    @Autowired
    private ManageLogEntityMapper manageLogEntityMapper;

    @Value("${token_expiresecs}")
    private String tokenExpiresecs;

    /**
     * 新增推广员
     *
     * @param bo
     * @return
     */
    @Override
    public int addSpreadUser(SpreadUserBO bo) {
        bo.setLoginPwd(md5Password(SpreadConstant.SPREAD_USER_DEFAULT_PASSWORD));
        return spreadUserEntityMapper.addSpreadUser(bo);
    }

    /**
     * 推广用户登录
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO spreadUserLogin(SpreadUserDto dto) {
        ResponseBO responseBO = new ResponseBO("登录成功");
        dto.setLoginPwd(md5Password(dto.getLoginPwd()));
        SpreadUserBO spreadUserBO = spreadUserEntityMapper.spreadUserlogin(dto);
        //登录成功
        if (spreadUserBO != null) {
            String token = genAccountLoginTokenId(spreadUserBO);
            spreadUserBO.setToken(token);
            responseBO.setData(spreadUserBO);
        } else {
            return new ResponseBO(ResponseCode.L003.getCode(),ResponseCode.L003.getTitle());
        }

        return responseBO;
    }

    /**
     * 修改密码
     *
     * @param bo
     * @return
     */
    @Override
    public ResponseBO changePassword(SpreadUserBO bo) {
        ResponseBO responseBO = new ResponseBO("修改成功");
        bo.setNewPassword(md5Password(bo.getNewPassword()));
        bo.setOldPassword(md5Password(bo.getOldPassword()));
        int result = spreadUserEntityMapper.changePassword(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.L004.getCode(), ResponseCode.L004.getTitle());
        }
        return responseBO;
    }

    /**
     * 退出登录
     *
     * @param token
     * @param spreadUserId
     * @return
     */
    @Override
    public void logout(String token, Integer spreadUserId) {
        String tokenKey = authService.getLoginTokenAccountCacheKey(token);
        String userIdKey = authService.getUseridLoginTokenCacheKey(spreadUserId+"");

        accessTokenRedisDao.remove(tokenKey);
        accessTokenRedisDao.remove(userIdKey);
    }

    @Override
    public ResponseBO getSpreadUserByQry(SpreadUserDto dto) {
        ResponseBO responseBO = new ResponseBO("查询成功");
        responseBO.setData(spreadUserEntityMapper.getSpreadUserByQry(dto));
        return responseBO;
    }

    /**
     * 查询推广用户列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO listSpreadUserByQry(SpreadUserDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(spreadUserEntityMapper.listSpreadUserByQry(dto));
        responseBO.setTotalCount(spreadUserEntityMapper.totalSpreadUserByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 重置密码
     *
     * @param bo
     * @return
     */
    @Override
    public int resetPassword(SpreadUserBO bo) {
        bo.setNewPassword(md5Password(SpreadConstant.SPREAD_USER_DEFAULT_PASSWORD));
        return spreadUserEntityMapper.resetPassword(bo);
    }

    /**
     * 取消推广员
     *
     * @param spreadUserId
     * @return
     */
    @Override
    public int cancelSpreadUserById(Integer spreadUserId) {
        return spreadUserEntityMapper.cancelSpreadUserById(spreadUserId);
    }

    /**
     * 重置推广员提成比率
     *
     * @param bo
     * @return
     */
    @Override
    public int resetSpreadUserRatio(SpreadUserBO bo) {
        bo.setRatio(SpreadConstant.SPREAD_USER_DEFAULT_RATIO);
        return spreadUserEntityMapper.resetSpreadUserRatio(bo);
    }

    /**
     * 调整推广员钻石
     *
     * @param bo
     * @return
     */
    @Override
    public int changeSpreadUserDiamond(SpreadUserBO bo) {
        int result = spreadUserEntityMapper.changeSpreadUserDiamond(bo);
        if (result > 0) {
            ManageLogEntity entity = new ManageLogEntity();
            entity.setManagerid(bo.getManagerId());
            entity.setManageTime(new Date());
            entity.setTypeid(261);
            entity.setManageDesc("管理员【"+bo.getManagerId()+"】削减推广员【"+bo.getId()+"】钻石数："+ bo.getReduceDiamond());
            manageLogEntityMapper.insertSelective(entity);
        }
        return result;
    }

    /**
     * 修改推广员信息
     *
     * @param bo
     * @return
     */
    @Override
    public int updateSpreadUser(SpreadUserBO bo) {
        return spreadUserEntityMapper.updateSpreadUser(bo);
    }

    /**
     * 生成token
     * @param spreadUserBO
     * @return
     */
    private String genAccountLoginTokenId(SpreadUserBO spreadUserBO) {
        AccessToken token = new AccessToken();
        String tokenId = UUID.randomUUID().toString().replaceAll("-", "");

        String userId = spreadUserBO.getId()+"";

        token.setCreatetime(CommonUtil.getCurrentSecondTime());
        token.setTokenid(tokenId);
        token.setUserid(userId);
        token.setLoginname(spreadUserBO.getLoginName());
        token.setExpiresecs(Long.valueOf(tokenExpiresecs));
        String useridKey = authService.getUseridLoginTokenCacheKey(userId);
        String tokenKey = authService.getLoginTokenAccountCacheKey(tokenId);
        //验证通过延长会话有效期
        accessTokenRedisDao.saveAsJSONStr(tokenKey, token, token.getExpiresecs());
        accessTokenRedisDao.saveAsJSONStr(useridKey, token, token.getExpiresecs());
        return tokenId;
    }

    /**
     * 密码md5加密
     * @param password
     * @return
     */
    private String md5Password(String password) {
        return MD5Util.encode(password+"Esdpf@666888").toUpperCase();
    }
}
