package com.itsocoo.multidatasource.jdbc.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghaibo
 * @version V1.0
 * @desc
 * @date 2019/4/11 16:31
 */
public class MultiDataSourceBeanPostProcess implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(MultiDataSourceBeanPostProcess.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // logger.info("===============>1111 beanName:{}",beanName);
        if (beanName.equalsIgnoreCase("jdbcTemplateMap")) {
            Map<String, JdbcTemplate> jdbcTemplateMap = (Map<String, JdbcTemplate>) bean;

            System.out.println(jdbcTemplateMap);

            jdbcTemplateMap.forEach((s, jdbcTemplate) -> {

            });
        }

        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equalsIgnoreCase("jdbcTemplateMap")) {
            Map<String, JdbcTemplate> jdbcTemplateMap = (Map<String, JdbcTemplate>) bean;

            System.out.println(jdbcTemplateMap);
        }
        // logger.info("===============>2222 beanName:{}",beanName);
        return null;
    }
}
