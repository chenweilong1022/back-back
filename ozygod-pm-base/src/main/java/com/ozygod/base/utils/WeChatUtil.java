package com.ozygod.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ozygod.base.redis.StringRedisDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 获取微信短链接
     * @param longUrl
     * @return
     */
    public String getWeChatShortUrl(String longUrl) {
        // 先检查longUrl是否已经有对应的shortUrl
        String shortUrl = stringRedisDao.readStr(longUrl);
        if (!CommonUtil.isEmptyStr(shortUrl)) {
            return shortUrl;
        }
        // 检查redis是否存在accessToken
        String accessToken = stringRedisDao.readStr(accessTokenKey);
        // 当accessToken不存在时，从微信拉取数据
        if (CommonUtil.isEmptyStr(accessToken)) {
            String url = String.format(accessTokenUrl, appId, appSecret);
            String result = HttpRequestUtil.sendGet(url);
            log.info("result:" + result);
            if (!CommonUtil.isEmptyStr(result)) {
                Map<String, Object> resultMap = JSON.parseObject(result, new TypeReference<Map<String, Object>>(){});
                accessToken = String.valueOf(resultMap.get("access_token"));
                Integer expiresIn = (int)resultMap.get("expires_in");
                // 获取到后，保存在redis中
                stringRedisDao.saveStr(accessTokenKey, accessToken, expiresIn);
            }
        }
        // 根据长链接获取短链接
        String url = String.format(long2ShortUrl, accessToken);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("action", "long2short");
        paramMap.put("long_url", longUrl);
        String result = HttpRequestUtil.sendPostJson(url, JSON.toJSONString(paramMap));
        log.info("result:" + result);
        if (!CommonUtil.isEmptyStr(result)) {
            Map<String, Object> resultMap = JSON.parseObject(result, new TypeReference<Map<String, Object>>(){});
            shortUrl = String.valueOf(resultMap.get("short_url"));
            String errmsg = String.valueOf(resultMap.get("errmsg"));
            if ("ok".equals(errmsg)) {
                // 获取到后，保存在redis中，保存24小时
                stringRedisDao.saveStr(longUrl, shortUrl, 3600 * 24);
                return shortUrl;
            }
        }
        return "";
    }
}
