package com.itsocoo.multidatasource.jdbc.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;

/**
 * @author wanghaibo
 * @version V1.0
 * @desc
 * @date 2019/4/11 16:54
 */
public class MultiDataSourceBeanFactoryPostProcess implements BeanFactoryPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(MultiDataSourceBeanFactoryPostProcess.class);

    public MultiDataSourceBeanFactoryPostProcess() {
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        final String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        logger.info("===============>beanDefinitionNames:{}", Arrays.asList(beanDefinitionNames));

        // BeanDefinition beanDefinition = beanFactory.getBeanDefinition("jdbcTemplateMap");

        // logger.info("===============>beanDefinition:{}",beanDefinition);

    }
}
