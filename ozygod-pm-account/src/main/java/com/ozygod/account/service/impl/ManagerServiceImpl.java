package com.ozygod.account.service.impl;

import com.ozygod.account.service.IManagerService;
import com.ozygod.account.utils.ManageConstant;
import com.ozygod.base.auth.AccessToken;
import com.ozygod.base.auth.service.AuthService;
import com.ozygod.base.redis.auth.AccessTokenRedisDao;
import com.ozygod.base.utils.CommonUtil;
import com.ozygod.base.utils.Constant;
import com.ozygod.base.utils.MD5Util;
import com.ozygod.model.zdmanage.bo.ManageLogBO;
import com.ozygod.model.zdmanage.bo.ManagerBO;
import com.ozygod.model.zdmanage.dao.ManageLogEntityMapper;
import com.ozygod.model.zdmanage.dao.ManageLogTypeEntityMapper;
import com.ozygod.model.zdmanage.dao.ManagerEntityMapper;
import com.ozygod.model.zdmanage.dto.LoginDto;
import com.ozygod.model.zdmanage.dto.ManagerDto;
import com.ozygod.model.zdmanage.entity.ManageLogTypeEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/5
 */
@Component
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private ManagerEntityMapper managerEntityMapper;
    @Autowired
    private AuthService authService;
    @Autowired
    private AccessTokenRedisDao accessTokenRedisDao;
    @Autowired
    private ManageLogEntityMapper manageLogEntityMapper;
    @Autowired
    private ManageLogTypeEntityMapper manageLogTypeEntityMapper;

    @Value("${token_expiresecs}")
    private String tokenExpiresecs;

    @Override
    public ManagerBO login(LoginDto loginDto) {
        loginDto.setPassword(md5Password(loginDto.getPassword()));

        ManagerBO managerBO = managerEntityMapper.getManagerByLoginDto(loginDto);

        //调用存储过程记录日志
        managerEntityMapper.login(loginDto);

        //managerBO不为空，代表登录成功
        if (managerBO != null) {
            //生成token
            String token = genAccountLoginTokenId(managerBO, loginDto);
            managerBO.setToken(token);
            return managerBO;
        }

        return null;
    }

    /**
     * 生成token
     * @param managerBO
     * @param loginDto
     * @return
     */
    private String genAccountLoginTokenId(ManagerBO managerBO, LoginDto loginDto) {
        AccessToken token = new AccessToken();
        String tokenId = UUID.randomUUID().toString().replaceAll("-", "");

        String userId = managerBO.getManageid()+"";

        token.setCreatetime(CommonUtil.getCurrentSecondTime());
        token.setTokenid(tokenId);
        token.setUserid(userId);
        token.setLoginname(managerBO.getLoginName());
        //如果不是从PC端登录的，则登录时效为30天
        //如果PC端选择记住密码，则登录时效为7天，否则为1小时
        token.setExpiresecs(StringUtils.isEmpty(loginDto.getLoginFrom()) ? Long.valueOf(tokenExpiresecs) : 30 * 24 * 3600);
        String useridKey = authService.getUseridLoginTokenCacheKey(userId);
        String tokenKey = authService.getLoginTokenAccountCacheKey(tokenId);
        if (StringUtils.isNotEmpty(loginDto.getLoginFrom())) {
            //移动端
            useridKey += Constant.APP_LOGIN_SUFFIX;
            tokenKey += Constant.APP_LOGIN_SUFFIX;
        }
        //验证通过延长会话有效期
        accessTokenRedisDao.saveAsJSONStr(tokenKey, token, token.getExpiresecs());
        accessTokenRedisDao.saveAsJSONStr(useridKey, token, token.getExpiresecs());
        return tokenId;
    }

    @Override
    public ManagerBO getManagerById(Integer managerId) {
        return managerEntityMapper.getManagerById(managerId);
    }

    /**
     * 退出登录
     *
     * @param token
     * @param userId
     */
    @Override
    public void logout(String token, Integer userId) {
        String tokenKey = authService.getLoginTokenAccountCacheKey(token);
        String userIdKey = authService.getUseridLoginTokenCacheKey(userId+"");

        accessTokenRedisDao.remove(tokenKey);
        accessTokenRedisDao.remove(userIdKey);
    }

    @Override
    public int validateManager(ManagerBO managerBO) {
        managerBO.setOldPassword(md5Password(managerBO.getOldPassword()));
        return managerEntityMapper.validateManager(managerBO);
    }

    @Override
    public int changePassword(ManagerBO managerBO) {
        managerBO.setNewPassword(md5Password(managerBO.getNewPassword()));
        return managerEntityMapper.changePassword(managerBO);
    }

    /**
     * 查询管理人员列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<ManagerBO> listManagerByQry(ManagerDto dto) {
        return managerEntityMapper.listManagerByQry(dto);
    }

    /**
     * 删除管理用户
     *
     * @param dto
     * @return
     */
    @Override
    public int delManagerByQry(ManagerDto dto) {
        return managerEntityMapper.delManagerByQry(dto);
    }

    /**
     * 重置管理员密码
     *
     * @param bo
     * @return
     */
    @Override
    public int resetPassword(ManagerBO bo) {
        bo.setLoginPwd(md5Password(ManageConstant.MANAGE_DEFAULT_PASSWORD));
        return managerEntityMapper.addOrUpdateManager(bo);
    }

    /**
     * 新增或修改管理员信息
     *
     * @param bo
     * @return
     */
    @Override
    public int addManager(ManagerBO bo) {
        bo.setLoginPwd(md5Password(ManageConstant.MANAGE_DEFAULT_PASSWORD));

        return managerEntityMapper.addOrUpdateManager(bo);
    }

    /**
     * 修改管理员信息
     *
     * @param bo
     * @return
     */
    @Override
    public int updateManager(ManagerBO bo) {
        return managerEntityMapper.addOrUpdateManager(bo);
    }

    /**
     * 查询管理日志类型列表
     *
     * @return
     */
    @Override
    public List<ManageLogTypeEntity> listManageLogType() {
        return manageLogTypeEntityMapper.listManageLogType();
    }

    /**
     * 查询管理日志列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<ManageLogBO> listManageLogByQry(ManagerDto dto) {
        return manageLogEntityMapper.listManageLogByQry(dto);
    }

    /**
     * 查询管理日志列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalManageLogByQry(ManagerDto dto) {
        return manageLogEntityMapper.totalManageLogByQry(dto);
    }

    /**
     * 密码md5加密
     * @param password
     * @return
     */
    private String md5Password(String password) {
        return MD5Util.encode(password+"keyCxzU!.,").toUpperCase();
    }
}
