package com.ozygod.base.auth;

import com.ozygod.base.auth.service.AuthService;
import com.ozygod.base.redis.auth.AccessTokenRedisDao;
import com.ozygod.base.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * 类描述：登录token工具类
 *
 * @author by zhanggl on 2017/7/15.
 */
@Component
public class AccessTokenUtil implements Serializable {
    private static final long serialVersionUID = -1792042397989473876L;

    @Autowired
    private AuthService authService;

    private static AuthService staticAuthService;

    @Autowired
    private AccessTokenRedisDao accessTokenRedisDao;

    private static AccessTokenRedisDao staticAccessTokenRedisDao;

    /**
     * 单位：秒
     */
    private static String tokenExpiresecs;

    @Value("${token_expiresecs}")
    private void setTokenExpiresecs(String tokenExpiresecs){
        AccessTokenUtil.tokenExpiresecs = tokenExpiresecs;
    }

    private AccessTokenUtil() {
    }

    @PostConstruct
    public void init() {
        staticAccessTokenRedisDao = this.accessTokenRedisDao;
        staticAuthService = authService;
    }

    /**
     * 获取用户登录信息
     * @param token
     * @param loginfrom
     * @return
     */
    public static AccessToken getAccessToken(String token, String... loginfrom) {
        String tokenKey = staticAuthService.getLoginTokenAccountCacheKey(token);
        if (loginfrom != null && loginfrom.length!=0) {
            tokenKey += Constant.APP_LOGIN_SUFFIX;
        }
        AccessToken accessToken = staticAccessTokenRedisDao.readFromJSONStr(tokenKey);
        return accessToken;
    }

    /**
     * 刷新最新登录信息
     * @param accessToken
     * @param loginfrom
     */
    public static void resetAccessToken(AccessToken accessToken, String... loginfrom) {
        String useridKey = staticAuthService.getUseridLoginTokenCacheKey(accessToken.getUserid());
        String tokenKey = staticAuthService.getLoginTokenAccountCacheKey(accessToken.getTokenid());
        Long expireSecs = 0L;
        if (loginfrom == null || loginfrom.length==0) {
            //PC端
            expireSecs = Long.valueOf(tokenExpiresecs);
        } else {
            //移动端
            useridKey += Constant.APP_LOGIN_SUFFIX;
            //默认30天
            expireSecs = 30 * 24 * 3600L;
        }
        //验证通过延长会话有效期
        staticAccessTokenRedisDao.saveAsJSONStr(tokenKey, accessToken, expireSecs);
        staticAccessTokenRedisDao.saveAsJSONStr(useridKey, accessToken, expireSecs);
    }


}
