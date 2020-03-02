package com.ozygod.base.config;

import cn.hutool.core.io.resource.ClassPathResource;
import net.ipip.ipdb.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        City city = null;
        try {
            ClassPathResource resource = new ClassPathResource(ipdb);
            city = new City(resource.getStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }

}
