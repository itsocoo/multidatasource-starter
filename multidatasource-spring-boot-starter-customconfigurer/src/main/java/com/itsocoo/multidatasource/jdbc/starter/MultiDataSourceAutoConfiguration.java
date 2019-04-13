package com.itsocoo.multidatasource.jdbc.starter;

import com.itsocoo.multidatasource.jdbc.starter.config.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

/**
 * @author wanghaibo
 * @version V1.0
 * @Description 配置jdbc的多数据源
 * @date 2018/3/23 14:12
 */
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
//         DataSourceTransactionManagerAutoConfiguration.class,
//         JdbcTemplateAutoConfiguration.class})
@Configuration
@ConditionalOnProperty(name = "itsocoo.multi.datasource.enable.profile-type")
@EnableConfigurationProperties({MyDatasourceEnableProperties.class, MultiDataSourceProperties.class})
@Import({
        // MultiDataSourceImportBeanDefinitionRegistrar.class,
        // MultiDataSourceConfigurationSelector.class
        OnlyDataSourceConfiguration.class,
        BestsellerDataSourceConfiguration.class,
        BiDataSourceConfiguration.class,
        FolDataSourceConfiguration.class,
        FordumpDataSourceConfiguration.class,
        HybrisDataSourceConfiguration.class,
        JackjonesDataSourceConfiguration.class,
        JlindebergDataSourceConfiguration.class,
        NameitDataSourceConfiguration.class,
        SelectedDataSourceConfiguration.class,
        VeromodaDataSourceConfiguration.class
})
public class MultiDataSourceAutoConfiguration implements EnvironmentAware {
    public MultiDataSourceAutoConfiguration() {
    }

    // private final MyDatasourceEnableProperties myDatasourceEnableProperties;
    //
    // @Autowired(required = false)
    // public MultiDataSourceAutoConfiguration(MyDatasourceEnableProperties myDatasourceEnableProperties) {
    //     this.myDatasourceEnableProperties = myDatasourceEnableProperties;
    // }

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "sharding.jdbc.datasource.";

        StandardEnvironment standardEnv = (StandardEnvironment) environment;
        standardEnv.setIgnoreUnresolvableNestedPlaceholders(true);
        String dataSources = standardEnv.getProperty(prefix + "names");

        System.out.println(dataSources);

    }
}