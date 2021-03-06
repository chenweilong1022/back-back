package com.ozygod.account.web;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.model.zdconfig.CheckConfig;
import com.ozygod.model.zdconfig.entity.TblIpWhiteListEntity;
import com.ozygod.model.zdconfig.service.TblIpWhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 系统ip白名单配置
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-12-14 21:50:40
 */
@RestController
@RequestMapping("zdconfig")
public class TblIpWhiteListController {
    @Autowired
    private TblIpWhiteListService tblIpWhiteListService;

    @Autowired
    private CheckConfig checkConfig;


    /**
     * 页面访问之前先鉴权
     */
    @RequestMapping("tblipwhitelist/authenticate")
    public ResponseBO info(HttpServletRequest request){

        List<TblIpWhiteListEntity> list = tblIpWhiteListService.list(null);
        //如果不校验直接返回
        if (!checkConfig.getWhitelist()) {
            return ResponseBO.data(true);
        }

        if (CollectionUtil.isEmpty(list)) {
            return ResponseBO.data(false);
        }

        String clientIP = ServletUtil.getClientIP(request, "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP");

        String ips = list.stream().map(TblIpWhiteListEntity::getIp).collect(Collectors.joining(","));

        return ResponseBO.data(ips.contains(clientIP));
    }

    /**
     * 是否开启谷歌验证码
     */
    @RequestMapping("check/googlecode")
    public ResponseBO Googlecode(){
        return ResponseBO.data(checkConfig.getGooglecode());
    }


}
