package com.itsocoo.multidatasource.jdbc.starter;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;

/**
 * @author wanghaibo
 * @version V1.0
 * @desc 也可以一次注入这个类
 * @date 2019/4/8 18:18
 */
public class MultiDataSourceConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        String config = "com.itsocoo.multidatasource.jdbc.starter.config.%sDataSourceConfiguration";
        String[] platform = new String[]{"andy", "cindy", "john", "jack", "chris", "barry", "celia", "jones"};
        return Arrays.stream(platform).map(this::getHumpString).map(brand -> String.format(config, brand)).toArray(String[]::new);
    }

    private String getHumpString(String s) {
        String lowerCase = s.toLowerCase();
        String first = lowerCase.substring(0, 1).toUpperCase();
        String end = lowerCase.substring(1, lowerCase.length());
        return first + end;
    }

}
