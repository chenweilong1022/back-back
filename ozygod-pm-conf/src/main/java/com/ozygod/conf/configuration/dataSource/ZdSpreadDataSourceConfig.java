package com.ozygod.conf.configuration.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ozygod.conf.configuration.MybatisSqlSessionFactoryBeanConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @title: 推广平台数据源
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/6
 */
@Configuration
@MapperScan(basePackages = ZdSpreadDataSourceConfig.PACKAGES, sqlSessionFactoryRef = "zdspreadSqlSessionFactory")
public class ZdSpreadDataSourceConfig {

    private static final String ZDSPREAD_PREFIX = "spring.datasource.zdspread";
    static final String PACKAGES = "com.ozygod.model.zdspread.dao";
    private static final String MAPPER_LOCAL = "classpath:mapper/zdspread/*.xml";

    @Autowired
    private PaginationInterceptor page;

    @Bean(name = "zdspreadDataSource")
    @ConfigurationProperties(prefix = ZDSPREAD_PREFIX)
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "zdspreadTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(druidDataSource());
    }

    @Bean(name = "zdspreadSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("zdspreadDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = MybatisSqlSessionFactoryBeanConfig.getMybatisSqlSessionFactoryBean(dataSource, MAPPER_LOCAL, page);

        return sqlSessionFactoryBean.getObject();
    }
}
