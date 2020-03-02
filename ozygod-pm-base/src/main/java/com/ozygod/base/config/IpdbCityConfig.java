package com.ozygod.base.config;

import cn.hutool.core.io.file.FileReader;
import net.ipip.ipdb.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-03-02 13:26
 */
@Configuration
public class IpdbCityConfig {

    @Value("${ipdb}")
    private String ipdb;

    @Bean
    public City city() {
        FileReader fileReader = new FileReader(ipdb);
        BufferedInputStream inputStream = fileReader.getInputStream();
        City city = null;
        try {
            city = new City(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }

}
