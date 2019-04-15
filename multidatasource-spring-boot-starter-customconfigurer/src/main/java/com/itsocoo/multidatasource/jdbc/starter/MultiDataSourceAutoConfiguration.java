package com.itsocoo.multidatasource.jdbc.starter;

import com.itsocoo.multidatasource.jdbc.starter.properties.MultiDataSourceProperties;
import com.itsocoo.multidatasource.jdbc.starter.properties.MyDatasourceEnableProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author wanghaibo
 * @version V1.0
 * @Description 配置jdbc的多数据源
 * @date 2018/3/23 14:12
 */
@Configuration
@ConditionalOnProperty(name = "itsocoo.multi.datasource.enable.profile-type")
@EnableConfigurationProperties({MyDatasourceEnableProperties.class, MultiDataSourceProperties.class})
@Import({
        MultiDataSourceConfigurationSelector.class
        // 可以分别注入下面的
        // AndyDataSourceConfiguration.class,
        // CindyDataSourceConfiguration.class,
        // JohnDataSourceConfiguration.class,
        // JackDataSourceConfiguration.class,
        // ChrisDataSourceConfiguration.class,
        // BarryDataSourceConfiguration.class,
        // CeliaDataSourceConfiguration.class,
        // JonesDataSourceConfiguration.class,
})
public class MultiDataSourceAutoConfiguration {

}