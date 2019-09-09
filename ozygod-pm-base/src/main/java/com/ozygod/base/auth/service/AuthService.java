package com.ozygod.base.auth.service;

import com.ozygod.base.enums.CacheName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 权限服务类
 *
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    /**
     * 行键分隔符
     */
    private static final String ROW_KEY_SEP = "@";

    /**
     * 获取登录token缓存key
     *
     * @param tokenId
     * @return
     */
    public String getLoginTokenAccountCacheKey(String tokenId) {
        StringBuilder builder = new StringBuilder();
        builder.append(CacheName.LOGIN_TOKEN_ACCOUNT.getCode())
                .append(ROW_KEY_SEP)
                .append(tokenId);
        return builder.toString();
    }

    /**
     * 账号登录token缓存key
     *
     * @param accountId
     * @return
     */
    public String getAccountLoginTokenCacheKey(String accountId) {
        StringBuilder builder = new StringBuilder();
        builder.append(CacheName.ACCOUNT_LOGIN_TOKEN.getCode())
                .append(ROW_KEY_SEP)
                .append(accountId);
        return builder.toString();
    }

    /**
     * userid token缓存key
     *
     * @param userid
     * @return
     */
    public String getUseridLoginTokenCacheKey(String userid) {
        StringBuilder builder = new StringBuilder();
        builder.append(CacheName.USERID_LOGIN_TOKEN.getCode())
                .append(ROW_KEY_SEP)
                .append(userid);
        return builder.toString();
    }
}
