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
 * @desc nameit的数据库配置
 * @date 2018/8/7 10:48
 */
@MyConditionalOnProperty(name = "itsocoo.multi.datasource.enable.platform", havingValue = "nameit")
public class NameitDataSourceConfiguration {

    /**
     * nameit的数据库配置
     */
    @Bean(name = "nameitDataSource")
    @Qualifier("nameitDataSource")
    @ConfigurationProperties(prefix = "itsocoo.multi.datasource.nameit")
    public DataSource nameitDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "nameitJdbcTemplate")
    public JdbcTemplate nameitJdbcTemplate(@Qualifier("nameitDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "nameitPlatformTransactionManager")
    public PlatformTransactionManager nameitPlatformTransactionManager(@Qualifier("nameitDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
