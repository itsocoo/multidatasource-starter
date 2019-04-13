package com.itsocoo.multidatasource.jdbc.starter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author wanghaibo
 * @version V1.0
 * @desc
 * @date 2019/4/8 18:18
 */
public class MultiDataSourceConfigurationSelector implements ImportSelector, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        ConfigurableListableBeanFactory beanFactory = (ConfigurableListableBeanFactory) this.beanFactory;

        // final Object myDatasourceEnableProperties = beanFactory.getBean("myDatasourceEnableProperties");

        // System.out.println(222222);
        /*MyDatasourceEnableProperties properties = beanFactory.getBean("myDatasourceEnableProperties",MyDatasourceEnableProperties.class);
        String[] platform = properties.getPlatform();


        final String config1 = this.getClass().getClassLoader().getResource("config").getPath();
        System.out.println(config1);

        String config = "com.lzsz.multidatasource.jdbc.starter.config.%sLzszDataSourceConfiguration";

        return Arrays.stream(platform).map(this::getHumpString).map(s -> String.format(config, s)).toArray(String[]::new);*/
        return new String[]{};
    }

    private String getHumpString(String s) {
        String lowerCase = s.toLowerCase();
        String first = lowerCase.substring(0, 1).toUpperCase();
        String end = lowerCase.substring(1, lowerCase.length());
        return first + end;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        this.beanFactory = beanFactory;
    }

    public static void main(String[] args) {
        // String config = "com.lzsz.multidatasource.jdbc.starter.config.%sLzszDataSourceConfiguration";
        // String ssss = String.format(config, "ssss");
        // System.out.println(ssss);

        // String lowerCase = "abc".toLowerCase();
        // String first = lowerCase.substring(0,1).toUpperCase();
        // System.out.println(first);
        // String end = lowerCase.substring(1, lowerCase.length());
        // System.out.println(end);

        // final String s = first + end;

        // System.out.println(new MultiDataSourceConfigurationSelector().getHumpString("abc"));
        // System.out.println(UUID.randomUUID().toString());
    }
}
