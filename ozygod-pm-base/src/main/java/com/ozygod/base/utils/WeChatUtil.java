package com.ozygod.base.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ozygod.base.redis.StringRedisDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    private static final String wxLongUrlKey = "wxKey%s";
    /**
     * accessToken缓存key
     */
    private static final String accessTokenKey = "accessToken";

//    private static final String weibourl = "http://maiyurl.cn/weibourl?url_long=%s";
    private static final String weibourl = "http://mrw.so/api.htm?url=%s&key=5e13f75e44bb35504c95a497@de6946de2e3f0b436670c84992f25788&expireDate=%s";

    /**
     * 19-12-09
     * 陈伟龙修改
     * 将微信短链修改为其他平台短链
     * @param longUrl
     * @return
     */
    public String getWeChatShortUrl(String longUrl) {

        DateTime dateTime = DateUtil.offsetDay(new Date(), 7);
        String date = DateUtil.format(dateTime, "yyyy-MM-dd");


        // 先检查longUrl是否已经有对应的shortUrl
        String shortUrl = stringRedisDao.readStr(longUrl);
        if (!CommonUtil.isEmptyStr(shortUrl)) {
            return shortUrl;
        }

        String url = String.format(weibourl, longUrl,date);

        shortUrl = HttpUtil.get(url);

        if (!CommonUtil.isEmptyStr(shortUrl)) {
            stringRedisDao.saveStr(longUrl, shortUrl, 3600 * 24);
            return shortUrl;
        }

        return "";
    }


    /**
     * 获取微信短链接
     * @param longUrl
     * @return
     */
    public String getWeChatShortUrl1(String longUrl) {
        // 先检查longUrl是否已经有对应的shortUrl
//        String shortUrl = stringRedisDao.readStr(longUrl);
//        if (!CommonUtil.isEmptyStr(shortUrl)) {
//            return shortUrl;
//        }
        // 检查redis是否存在accessToken
        String accessToken = "";
        // 当accessToken不存在时，从微信拉取数据
//        if (CommonUtil.isEmptyStr(accessToken)) {
//            String url = ;
            String result1 = HttpRequestUtil.sendGet(String.format(accessTokenUrl, appId, appSecret));
            log.info("result:" + result1);
//            if (!CommonUtil.isEmptyStr(result)) {
                Map<String, Object> resultMap = JSON.parseObject(result1, new TypeReference<Map<String, Object>>(){});
                accessToken = String.valueOf(resultMap.get("access_token"));
                Integer expiresIn = (int)resultMap.get("expires_in");
                // 获取到后，保存在redis中
//                stringRedisDao.saveStr(accessTokenKey, accessToken, expiresIn);
//            }
//        }
        // 根据长链接获取短链接
//        String url = ;
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("action", "long2short");
//        paramMap.put("long_url", longUrl);
//        String result = HttpRequestUtil.sendPostJson(String.format(long2ShortUrl, accessToken), JSON.toJSONString(paramMap));
//        log.info("result:" + result);
//        if (!CommonUtil.isEmptyStr(result)) {
//            Map<String, Object> resultMap = JSON.parseObject(result, new TypeReference<Map<String, Object>>(){});
//            shortUrl = String.valueOf(resultMap.get("short_url"));
//            String errmsg = String.valueOf(resultMap.get("errmsg"));
//            if ("ok".equals(errmsg)) {
//                // 获取到后，保存在redis中，保存24小时
//                stringRedisDao.saveStr(longUrl, shortUrl, 3600 * 24);
//                return shortUrl;
//            }
//        }
//        return "";
        return  "";
    }

    /**
     * 判断长域名是否封禁
     * @param longUrl
     * @return
     */
    public boolean isBlocked(String longUrl) {
        /**
         * 获取微信token
         */
        String accessToken = stringRedisDao.readStr(accessTokenKey);
        if (StrUtil.isBlank(accessToken)) {
            String result = HttpUtil.get(String.format(accessTokenUrl, appId, appSecret));

            if (StrUtil.isNotBlank(result)) {
                Map<String, Object> resultMap = JSON.parseObject(result, new TypeReference<Map<String, Object>>(){});
                accessToken = String.valueOf(resultMap.get("access_token"));
                Integer expiresIn = (int)resultMap.get("expires_in");
                // 获取到后，保存在redis中
                stringRedisDao.saveStr(accessTokenKey, accessToken, expiresIn);
            }
        }
        /**
         * 判断redis是否存在
         */
        String shortUrl = stringRedisDao.readStr(String.format(wxLongUrlKey, longUrl));
        /**
         * 如果为空重新获取
         */
        if (StrUtil.isBlank(shortUrl)) {
            /**
             * 向微信发送转短链接
             */
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("action", "long2short");
            paramMap.put("long_url", longUrl);
            String result = HttpRequestUtil.sendPostJson(String.format(long2ShortUrl,accessToken), JSON.toJSONString(paramMap));
            log.info("result:" + result);
            /**
             * 判断是否为空不为空存到redis
             */
            if (StrUtil.isNotBlank(result)) {
                Map<String, Object> resultMap = JSON.parseObject(result, new TypeReference<Map<String, Object>>(){});
                shortUrl = String.valueOf(resultMap.get("short_url"));
                String errmsg = String.valueOf(resultMap.get("errmsg"));
                stringRedisDao.saveStr(String.format(wxLongUrlKey, longUrl),shortUrl,60 * 10);
            }
        }
        /**
         * 向短链接发送http请求
         * 如果存在 res.wx.qq.com/a/wx110/webpage-intercept/res/js/chunk-vendors.bf498f79.js\\
         * 说明域名被封
         * 如果没有说明域名正常
         */
        String isWx110 = HttpRequestUtil.sendGet(shortUrl);
        if (isWx110.contains("res.wx.qq.com/a/wx110/webpage-intercept/res/js/chunk-vendors.bf498f79.js")) {
            return true;
        }
        return false;
    }


}
