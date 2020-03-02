package com.ozygod.base.utils;

import cn.hutool.core.io.file.FileReader;
import com.ozygod.base.config.IpdbCityConfig;
import lombok.extern.slf4j.Slf4j;
import net.ipip.ipdb.City;
import net.ipip.ipdb.CityInfo;
import net.ipip.ipdb.IPFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2019-02-24
 */
@Component
@Slf4j
public class IPUtils {

    @Value("${ipdb}")
    public String ipdb;

    @Autowired
    private City city;

    /**
     * 获取IP归属地信息
     * @param ip
     * @return
     */
    public String getIPAddrCN(String ip) {
        try {
            CityInfo cityInfo = city.findInfo(ip, "CN");
            if (cityInfo == null) {
                return "未知";
            }
            String ipAttr = cityInfo.getCountryName() + "-" + cityInfo.getRegionName() + "-" + cityInfo.getCityName();
            if ("---".equals(ipAttr)) {
                return "未知";
            }
            return ipAttr;
        } catch (IOException | IPFormatException ex) {
            log.error(ex.getMessage());
        }

        return "未知";
    }
}
