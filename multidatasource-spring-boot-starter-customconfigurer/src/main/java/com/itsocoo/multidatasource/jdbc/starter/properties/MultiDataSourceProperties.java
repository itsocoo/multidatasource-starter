package com.itsocoo.multidatasource.jdbc.starter.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author wanghaibo
 * @version V1.0
 * @Description lzsz.multi.datasource.type :[dev/pro]
 * @date 2018/3/4 14:52
 */
@Component
@ConditionalOnBean(MyDatasourceEnableProperties.class)
@PropertySource(value = {"classpath:multi-datasource-${itsocoo.multi.datasource.enable.profile-type:pro}.properties"})
@ConfigurationProperties(prefix = MultiDataSourceProperties.MULTI_DATASOURCE_PREFIX)
public class MultiDataSourceProperties {
    static final String MULTI_DATASOURCE_PREFIX = "multi.datasource";

    private String[] enable;

    public String[] getEnable() {
        return enable;
    }

    public void setEnable(String[] enable) {
        this.enable = enable;
    }
}