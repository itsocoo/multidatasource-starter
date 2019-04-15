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
 * @desc jones的数据库配置
 * @date 2018/8/7 10:48
 */
@MyConditionalOnProperty(name = "itsocoo.multi.datasource.enable.platform", havingValue = "jones")
public class JonesDataSourceConfiguration {

    /**
     * jones的数据库配置
     */
    @Bean(name = "jonesDataSource")
    @Qualifier("jonesDataSource")
    @ConfigurationProperties(prefix = "multi.datasource.jones")
    public DataSource jonesDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jonesJdbcTemplate")
    public JdbcTemplate jonesJdbcTemplate(@Qualifier("jonesDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "jonesPlatformTransactionManager")
    public PlatformTransactionManager jonesPlatformTransactionManager(@Qualifier("jonesDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
