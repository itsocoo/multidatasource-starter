package com.itsocoo.multidatasource.jdbc.starter.config;

import com.itsocoo.multidatasource.jdbc.starter.MyConditionalOnProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author wanghaibo
 * @version V1.0
 * @desc selected的数据库配置
 * @date 2018/8/7 10:48
 */
@MyConditionalOnProperty(name = "itsocoo.multi.datasource.enable.platform", havingValue = "selected")
public class SelectedDataSourceConfiguration {

    /**
     * selected的数据库配置
     */
    @Bean(name = "selectedDataSource")
    @Qualifier("selectedDataSource")
    @ConfigurationProperties(prefix = "itsocoo.multi.datasource.selected")
    public DataSource selectedDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "selectedJdbcTemplate")
    public JdbcTemplate selectedJdbcTemplate(@Qualifier("selectedDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "selectedPlatformTransactionManager")
    public PlatformTransactionManager selectedPlatformTransactionManager(@Qualifier("selectedDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
