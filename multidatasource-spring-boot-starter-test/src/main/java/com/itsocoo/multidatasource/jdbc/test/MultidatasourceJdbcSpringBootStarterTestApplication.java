package com.itsocoo.multidatasource.jdbc.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

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
