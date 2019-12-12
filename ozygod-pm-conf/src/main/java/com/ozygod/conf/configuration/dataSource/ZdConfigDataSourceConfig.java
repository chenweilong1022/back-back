package com.ozygod.conf.configuration.dataSource;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @title: 管理平台数据源
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/6
 */
@Configuration
@MapperScan(basePackages = ZdConfigDataSourceConfig.PACKAGES, sqlSessionFactoryRef = "zdconfigSqlSessionFactory")
public class ZdConfigDataSourceConfig {

    private static final String ZDMANAGE_PREFIX = "spring.datasource.zdconfig";
    static final String PACKAGES = "com.ozygod.model.zdconfig.dao";
    private static final String MAPPER_LOCAL = "classpath:mapper/zdconfig/*.xml";

    @Bean(name = "zdconfigDataSource", initMethod = "init")
    @ConfigurationProperties(prefix = ZDMANAGE_PREFIX)
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "zdconfigTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(){

        return new DataSourceTransactionManager(druidDataSource());
    }

    @Bean(name = "zdconfigSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("zdconfigDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));


        MybatisConfiguration configuration = new  MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setLogPrefix("dao.");
        configuration.setUseColumnLabel(true);
        configuration.setCallSettersOnNulls(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean.getObject();
    }

}
