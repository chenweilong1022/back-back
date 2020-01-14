package com.ozygod.conf.configuration;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-14 16:45
 */
public class MybatisSqlSessionFactoryBeanConfig {

    public static MybatisSqlSessionFactoryBean getMybatisSqlSessionFactoryBean(DataSource dataSource, String MAPPER_LOCAL,PaginationInterceptor page) throws IOException {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));

        PaginationInterceptor[] paginationInterceptors = new PaginationInterceptor[1];
        paginationInterceptors[0] = page;
        sqlSessionFactoryBean.setPlugins(paginationInterceptors);


        MybatisConfiguration configuration = new  MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setLogPrefix("dao.");
        configuration.setUseColumnLabel(true);
        configuration.setCallSettersOnNulls(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }

}
