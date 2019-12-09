package com.ozygod.base.utils;

import com.ozygod.base.redis.StringRedisDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-04-25
 */
@Component
@Slf4j
public class WeChatUtil {
    @Autowired
    private StringRedisDao stringRedisDao;

    @Value("${appId}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

    /**
     * 获取accessToken地址
     */
    private static final String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    private static final String long2ShortUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=%s";
    /**
     * accessToken缓存key
     */
    private static final String accessTokenKey = "accessToken";

    private static final String weibourl = "http://maiyurl.cn/weibourl?url_long=%s";

    /**
     * 19-12-09
     * 陈伟龙修改
     * 将微信短链修改为其他平台短链
     * @param longUrl
     * @return
     */
    public String getWeChatShortUrl(String longUrl) {
        // 先检查longUrl是否已经有对应的shortUrl
        String shortUrl = stringRedisDao.readStr(longUrl);
        if (!CommonUtil.isEmptyStr(shortUrl)) {
            return shortUrl;
        }

        String url = String.format(weibourl, longUrl);

        shortUrl = HttpRequestUtil.sendGet(url);

        if (!CommonUtil.isEmptyStr(shortUrl)) {
            stringRedisDao.saveStr(longUrl, shortUrl, 3600 * 24);
            return shortUrl;
        }

        return "";
    }

}
