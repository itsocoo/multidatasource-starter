package com.itsocoo.multidatasource.jdbc.starter.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghaibo
 * @version V1.0
 * @Description lzsz.multi.datasource.type :[dev/pro]
 * @date 2018/3/4 14:52
 */
@Component
@ConditionalOnBean(MyDatasourceEnableProperties.class)
@PropertySource(value = {"classpath:itsocoo-multi-datasources-${itsocoo.multi.datasources.enable.profile-type:pro}.properties"})
@ConfigurationProperties(prefix = MultiDataSourceProperties.MULTI_DATASOURCE_PREFIX)
public class MultiDataSourceProperties {
    static final String MULTI_DATASOURCE_PREFIX = "itsocoo.multi";

    // private String[] enable;
    private Map<String, DruidDataSource> datasources = new HashMap<>();
    //
    // public String[] getEnable() {
    //     return enable;
    // }
    //
    // public void setEnable(String[] enable) {
    //     this.enable = enable;
    // }

    public Map<String, DruidDataSource> getDatasources() {
        return datasources;
    }

    public void setDatasources(Map<String, DruidDataSource> datasources) {
        this.datasources = datasources;
    }
}