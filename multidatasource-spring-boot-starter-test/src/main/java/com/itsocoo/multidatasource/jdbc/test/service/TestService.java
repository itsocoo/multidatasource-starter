package com.itsocoo.multidatasource.jdbc.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wanghaibo
 * @version V1.0
 * @desc
 * @date 2019/4/15 14:36
 */
@Service
public class TestService {
    // 注入一个
    @Autowired
    private JdbcTemplate andyJdbcTemplate;

    // 注入全部
    @Autowired
    private Map<String, JdbcTemplate> jdbcTemplateMap;
}
