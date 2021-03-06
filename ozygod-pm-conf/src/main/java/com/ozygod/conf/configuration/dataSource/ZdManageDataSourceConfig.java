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
import org.springframework.context.annotation.Primary;
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
@MapperScan(basePackages = ZdManageDataSourceConfig.PACKAGES, sqlSessionFactoryRef = "zdmanagerSqlSessionFactory")
public class ZdManageDataSourceConfig {

    private static final String ZDMANAGE_PREFIX = "spring.datasource.zdmanage";
    static final String PACKAGES = "com.ozygod.model.zdmanage.dao";
    private static final String MAPPER_LOCAL = "classpath:mapper/zdmanage/*.xml";

    @Autowired
    private PaginationInterceptor page;

    @Bean(name = "zdmanageDataSource", initMethod = "init")
    @Primary
    @ConfigurationProperties(prefix = ZDMANAGE_PREFIX)
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "zdmanageTransactionManager")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(druidDataSource());
    }

    @Bean(name = "zdmanagerSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("zdmanageDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = MybatisSqlSessionFactoryBeanConfig.getMybatisSqlSessionFactoryBean(dataSource, MAPPER_LOCAL, page);

        return sqlSessionFactoryBean.getObject();
    }

}
