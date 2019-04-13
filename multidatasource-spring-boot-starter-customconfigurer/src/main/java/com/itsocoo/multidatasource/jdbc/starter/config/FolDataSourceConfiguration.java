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
 * @desc fol的数据库配置
 * @date 2018/8/7 10:48
 */
@MyConditionalOnProperty(name = "itsocoo.multi.datasource.enable.platform", havingValue = "fol")
public class FolDataSourceConfiguration {

    /**
     * fol的数据库配置
     */
    @Bean(name = "folDataSource")
    @Qualifier("folDataSource")
    @ConfigurationProperties(prefix = "itsocoo.multi.datasource.fol")
    public DataSource folDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "folJdbcTemplate")
    public JdbcTemplate folJdbcTemplate(@Qualifier("folDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "folPlatformTransactionManager")
    public PlatformTransactionManager folPlatformTransactionManager(@Qualifier("folDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
