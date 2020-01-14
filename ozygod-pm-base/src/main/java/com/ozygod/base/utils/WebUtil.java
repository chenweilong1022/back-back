package com.ozygod.base.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-14 14:53
 */
public class WebUtil {

    /**
     * 获取HttpServletRequest
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }
}
