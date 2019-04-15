package com.itsocoo.multidatasource.jdbc.starter.config;

import com.itsocoo.multidatasource.jdbc.starter.properties.MyConditionalOnProperty;
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
 * @desc cindy的数据库配置
 * @date 2018/8/7 10:48
 */
@MyConditionalOnProperty(name = "itsocoo.multi.datasource.enable.platform", havingValue = "cindy")
public class CindyDataSourceConfiguration {

    /**
     * cindy的数据库配置
     */
    @Bean(name = "cindyDataSource")
    @Qualifier("cindyDataSource")
    @ConfigurationProperties(prefix = "multi.datasource.cindy")
    public DataSource cindyDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "cindyJdbcTemplate")
    public JdbcTemplate cindyJdbcTemplate(@Qualifier("cindyDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "cindyDataSourceTransactionManager")
    public PlatformTransactionManager DataSourceTransactionManager(@Qualifier("cindyDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
