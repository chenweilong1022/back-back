package com.ozygod.conf.configuration.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
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
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/12
 */
@Configuration
@MapperScan(basePackages = ZdLogDataSourceConfig.PACKAGES, sqlSessionFactoryRef = "zdlogSqlSessionFactory")
public class ZdLogDataSourceConfig {
    private static final String ZDLOG_PREFIX = "spring.datasource.zdlog";
    static final String PACKAGES = "com.ozygod.model.zdlog.dao";
    private static final String MAPPER_LOCAL = "classpath:mapper/zdlog/*.xml";

    @Autowired
    private PaginationInterceptor page;

    @Bean(name = "zdlogDataSource")
    @ConfigurationProperties(prefix = ZDLOG_PREFIX)
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "zdlogTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(druidDataSource());
    }

    @Bean(name = "zdlogSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("zdlogDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = MybatisSqlSessionFactoryBeanConfig.getMybatisSqlSessionFactoryBean(dataSource, MAPPER_LOCAL, page);

        return sqlSessionFactoryBean.getObject();
    }
}
