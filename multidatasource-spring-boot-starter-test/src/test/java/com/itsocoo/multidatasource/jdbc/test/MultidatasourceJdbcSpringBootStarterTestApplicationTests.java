package com.itsocoo.multidatasource.jdbc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MultidatasourceJdbcSpringBootStarterTestApplicationTests {


    @Autowired
    @Qualifier("biLzszJdbcTemplate")
    private JdbcTemplate onlyJdbcTemplate;

    @Test
    public void test() throws IOException {
        List<Map<String, Object>> mapList = onlyJdbcTemplate.queryForList("select * from shop_hybris limit 1");
        System.out.println(mapList);
    }

}
