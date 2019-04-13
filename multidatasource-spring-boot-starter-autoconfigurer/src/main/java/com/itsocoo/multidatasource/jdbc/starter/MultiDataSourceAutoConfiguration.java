package com.itsocoo.multidatasource.jdbc.starter;

import com.alibaba.druid.pool.DruidDataSource;
import com.itsocoo.multidatasource.jdbc.starter.properties.MultiDataSourceProperties;
import com.itsocoo.multidatasource.jdbc.starter.properties.MyDatasourceEnableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghaibo
 * @version V1.0
 * @desc 配置jdbc的多数据源
 * @date 2019/4/10 15:26
 */
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
//         DataSourceTransactionManagerAutoConfiguration.class,
//         JdbcTemplateAutoConfiguration.class})
@Configuration
@ConditionalOnProperty(name = "itsocoo.multi.datasources.enable.profile-type")
@EnableConfigurationProperties({MyDatasourceEnableProperties.class, MultiDataSourceProperties.class})
public class MultiDataSourceAutoConfiguration implements EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(MultiDataSourceAutoConfiguration.class);

    // private final MyDatasourceEnableProperties myDatasourceEnableProperties;
    //
    // @Autowired(required = false)
    // public MultiDataSourceAutoConfiguration(MyDatasourceEnableProperties myDatasourceEnableProperties) {
    //     this.myDatasourceEnableProperties = myDatasourceEnableProperties;
    // }

    // @Autowired(required = false)
    // private MyDatasourceEnableProperties myDatasourceEnableProperties;

    @Bean
    public MultiDataSourceBeanPostProcess multiDataSourceBeanPostProcess() {
        return new MultiDataSourceBeanPostProcess();
    }

    // @Bean
    // public MultiDataSourceBeanFactoryPostProcess multiDataSourceBeanFactoryPostProcess() {
    //     return new MultiDataSourceBeanFactoryPostProcess();
    // }

    private final MultiDataSourceProperties dataSourceProperties;

    @Autowired(required = false)
    public MultiDataSourceAutoConfiguration(MultiDataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "itsocoo.multi.datasources.";

        StandardEnvironment standardEnv = (StandardEnvironment) environment;
        standardEnv.setIgnoreUnresolvableNestedPlaceholders(true);
        String dataSources = standardEnv.getProperty(prefix + "names");

        logger.info("===============>dataSources:{}", dataSources);
    }

    @Bean
    public Map<String, DataSource> dataSourceMap() {
        // System.out.println(myDatasourceEnableProperties);
        System.out.println(dataSourceProperties);
        dataSourceProperties.getDatasources().forEach((k, v) -> configDataSource(v));

        System.out.println(dataSourceProperties);
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.putAll(dataSourceProperties.getDatasources());

        logger.info("===============>dataSourceMap:{}", dataSourceMap);

        return dataSourceMap;
    }

    @Bean
    public Map<String, JdbcTemplate> jdbcTemplateMap() {
        Map<String, JdbcTemplate> templateHashMap = new HashMap<>();


        Map<String, DataSource> stringDataSourceMap = dataSourceMap();
        stringDataSourceMap.forEach((s, dataSource) -> {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            templateHashMap.put(s + "JdbcTemplate", jdbcTemplate);
        });


        return templateHashMap;
    }

    @Bean
    Map<String, PlatformTransactionManager> platformTransactionManagerMap() {
        Map<String, PlatformTransactionManager> managerMap = new HashMap<>();

        Map<String, DataSource> stringDataSourceMap = dataSourceMap();
        stringDataSourceMap.forEach((s, dataSource) -> {
            PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(dataSource);
            managerMap.put(s + "PlatformTransactionManager", platformTransactionManager);
        });

        return managerMap;
    }

    private void configDataSource(DruidDataSource druidDataSource) {
        druidDataSource.setMaxActive(20);
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("select 'x'");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxOpenPreparedStatements(20);
        druidDataSource.setUseGlobalDataSourceStat(true);
        try {
            druidDataSource.setFilters("stat,wall,slf4j");
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
    }
}