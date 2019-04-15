---
title: 多数据源代码的聚合
date: 2019-04-15
categories: ['心得']
tags: ['心得','分享']
comments: true
---

####1.multidatasource-spring-boot-starter-customconfigurer 手工装配数据源

> 将多个数据源配置成jar供其他项目引用即可，无需每个项目写多余代码

1.默认不关闭：

```java
DataSourceAutoConfiguration.class,
DataSourceTransactionManagerAutoConfiguration.class,
JdbcTemplateAutoConfiguration.class
```

2.每个品牌的库注入上面三个组件 命名规则如下：

// andyDataSource 
// andyJdbcTemplate
// andyPlatformTransactionManager
// 规则：brand+(DataSource/JdbcTemplate/PlatformTransactionManager)

```java
@MyConditionalOnProperty(name = "itsocoo.multi.datasource.enable.platform", havingValue = "andy")
public class AndyDataSourceConfiguration {

    /**
     * andy的数据库配置
     */
    @Bean(name = "andyDataSource")
    @Qualifier("andyDataSource")
    @ConfigurationProperties(prefix = "multi.datasource.andy")
    public DataSource andyDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "andyJdbcTemplate")
    public JdbcTemplate andyJdbcTemplate(@Qualifier("andyDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "andyPlatformTransactionManager")
    public PlatformTransactionManager andyPlatformTransactionManager(@Qualifier("andyDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
```

* 改写multidatasource-spring-boot-starter-customconfigurer文件 一路仿照代码实现自己的就可以了 然后打包

* 使用前先引入包

  ```xml
  <dependency>
      <groupId>com.itsocoo</groupId>
      <artifactId>multidatasource-jdbctemplate-spring-boot-starter</artifactId>
      <version>2.1.3.RELEASE</version>
  </dependency>
  ```

* 配置application.properties 

  ```properties
  # 目前是三种环境：dev、local、pro 分别对应multidatasource-spring-boot-starter-customconfigurer 中配置的 multi-datasource-dev.properties文件
  # 不设置会默认选择Pro环境
  itsocoo.multi.datasource.enable.profile-type=dev
  # 使用什么库就配置什么库 不配的spring不注入
  itsocoo.multi.datasource.enable.platform=andy,cindy,john,jack,chris,barry,celia,jones
  ```

* 使用：

  ```java
  @Service
  public class TestService {
      // 注入一个
      @Autowired
      private JdbcTemplate andyJdbcTemplate;
      
      // 注入全部
      @Autowired
      private Map<String, JdbcTemplate> jdbcTemplateMap;
  }
  ```

* 测试代码在multidatasource-spring-boot-starter-test中

  ```java
  @SpringBootApplication
          (exclude = {DataSourceAutoConfiguration.class,
                  DataSourceTransactionManagerAutoConfiguration.class,
                  JdbcTemplateAutoConfiguration.class})
  public class MultidatasourceJdbcSpringBootStarterTestApplication {
      private static final Logger logger = LoggerFactory.getLogger(MultidatasourceJdbcSpringBootStarterTestApplication.class);
  
      public static void main(String[] args) {
          ConfigurableApplicationContext context = SpringApplication.run(MultidatasourceJdbcSpringBootStarterTestApplication.class, args);
          final Map<String, DataSource> dataSourceMap = context.getBeansOfType(DataSource.class);
  
          System.out.println(dataSourceMap);
          logger.info("===============>dataSourceMap:{}",dataSourceMap);
  
          final Map<String, JdbcTemplate> jdbcTemplateMap = context.getBeansOfType(JdbcTemplate.class);
  
          logger.info("===============>jdbcTemplateMap:{}",jdbcTemplateMap);
  
          // JdbcTemplate andyJdbcTemplate = context.getBean("andyJdbcTemplate", JdbcTemplate.class);
          // System.out.println(andyJdbcTemplate);
  
          jdbcTemplateMap.forEach((brand, jdbcTemplate) -> {
              List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from multi_test limit 1");
              logger.info("===============>brand={}:{}", brand, mapList);
          });
  
      }
  }
  ```

* 测试的sql在sql文件夹中

####2.multidatasource-spring-boot-starter-autoconfigurer 自动装配数据源（测试阶段）





**具体参考[github](https://github.com/itsocoo/multidatasource-starter)代码**





