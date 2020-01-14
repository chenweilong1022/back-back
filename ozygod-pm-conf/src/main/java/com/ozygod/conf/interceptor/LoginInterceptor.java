package com.ozygod.conf.interceptor;

import cn.hutool.core.date.DateUtil;
import com.ozygod.base.auth.AccessToken;
import com.ozygod.base.auth.AccessTokenUtil;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.Global;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/5
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {


    private static final ThreadLocal<AccessToken> ACCESS_LOG = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-Token");
        log.debug("用户请求鉴权token = {} currentTime = {}", token, DateUtil.formatDateTime(new Date()));

        if(StringUtils.isNotEmpty(token))   {
            String loginfrom = request.getHeader("loginFrom");
            log.debug("用户请求登录鉴权loginFrom = {}",loginfrom);
            AccessToken accessToken = StringUtils.isEmpty(loginfrom) ? AccessTokenUtil.getAccessToken(token) : AccessTokenUtil.getAccessToken(token, "1");
            if (accessToken != null) {
                if (StringUtils.isEmpty(loginfrom)) {
                    AccessTokenUtil.resetAccessToken(accessToken);
                } else {
                    AccessTokenUtil.resetAccessToken(accessToken, loginfrom);
                }
                request.setAttribute(Global.ACCESS_TOKEN_REQUEST_KEY,accessToken);
                return true;
            }
        }
        response.setContentType("text/html; charset=UTF-8");
        ResponseBO bo = new ResponseBO(ResponseCode.L001.getCode(), ResponseCode.L001.getTitle());
        response.getWriter().write(JsonUtil.toJSONString(bo, true));
        return false;
    }

}
