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
 * @desc barry的数据库配置
 * @date 2018/8/7 10:48
 */
@MyConditionalOnProperty(name = "itsocoo.multi.datasource.enable.platform", havingValue = "barry")
public class BarryDataSourceConfiguration {
    /**
     * barry的数据库配置
     */
    @Bean(name = "barryDataSource")
    @Qualifier("barryDataSource")
    @ConfigurationProperties(prefix = "multi.datasource.barry")
    public DataSource barryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "barryJdbcTemplate")
    public JdbcTemplate barryJdbcTemplate(@Qualifier("barryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "barryPlatformTransactionManager")
    public PlatformTransactionManager barryPlatformTransactionManager(@Qualifier("barryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
