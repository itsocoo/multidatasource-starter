package com.itsocoo.multidatasource.jdbc.test;

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


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MultidatasourceJdbcSpringBootStarterTestApplication.class, args);
        // final Map<String, DataSource> beansOfType = context.getBeansOfType(DataSource.class);
        //
        // System.out.println(beansOfType);

        // final Map<String, JdbcTemplate> beansOfType = context.getBeansOfType(JdbcTemplate.class);
        //
        // System.out.println(beansOfType);

        // JdbcTemplate onlyJdbcTemplate = context.getBean("folJdbcTemplate", JdbcTemplate.class);
        // System.out.println(onlyJdbcTemplate);
        //
        // List<Map<String, Object>> mapList = onlyJdbcTemplate.queryForList("select * from big_order limit 1");
        //
        // System.out.println(mapList);

        // final MyDatasourceEnableProperties bean = context.getBean(MyDatasourceEnableProperties.class);

        // JdbcTemplate jackjonesLzszJdbcTemplate = context.getBean("jackjonesLzszJdbcTemplate", JdbcTemplate.class);
        //
        // List<Map<String, Object>> mapList1 = jackjonesLzszJdbcTemplate.queryForList("select * from big_order limit 1");
        //
        // System.out.println(mapList1);

        // JdbcTemplate jlindebergLzszJdbcTemplate = context.getBean("jlindebergLzszJdbcTemplate", JdbcTemplate.class);
        //
        // List<Map<String, Object>> mapList2 = jlindebergLzszJdbcTemplate.queryForList("select * from big_order limit 1");
        //
        // System.out.println(mapList2);

        // JdbcTemplate onlyJdbcTemplate = context.getBean("fordumpLzszJdbcTemplate", JdbcTemplate.class);
        // System.out.println(onlyJdbcTemplate);
    }
}
