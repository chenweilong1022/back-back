package com.ozygod.conf.configuration.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
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
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setLogPrefix("dao.");
        configuration.setUseColumnLabel(true);
        configuration.setCallSettersOnNulls(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean.getObject();
    }
}
