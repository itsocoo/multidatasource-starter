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
 * @desc jlindeberg的数据库配置
 * @date 2018/8/7 10:48
 */
@MyConditionalOnProperty(name = "itsocoo.multi.datasource.enable.platform", havingValue = "jlindeberg")
public class JlindebergDataSourceConfiguration {

    /**
     * jlindeberg的数据库配置
     */
    @Bean(name = "jlindebergDataSource")
    @Qualifier("jlindebergDataSource")
    @ConfigurationProperties(prefix = "itsocoo.multi.datasource.jlindeberg")
    public DataSource jlindebergDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jlindebergJdbcTemplate")
    public JdbcTemplate jlindebergJdbcTemplate(@Qualifier("jlindebergDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "jlindebergPlatformTransactionManager")
    public PlatformTransactionManager jlindebergPlatformTransactionManager(@Qualifier("jlindebergDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
