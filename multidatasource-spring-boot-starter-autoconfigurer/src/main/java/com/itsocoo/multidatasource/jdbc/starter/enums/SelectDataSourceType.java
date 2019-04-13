package com.itsocoo.multidatasource.jdbc.starter.enums;

/**
 * @author wanghaibo
 * @version V1.0
 * @desc 可执行的所有数据源名称
 * @date 2018/9/6 17:31
 */
public enum SelectDataSourceType {

    JACKJONES("jackjonesJdbcTemplate", "jackjonesPlatformTransactionManager"),
    SELECTED("selectedJdbcTemplate", "selectedPlatformTransactionManager"),
    ONLY("onlyJdbcTemplate", "onlyPlatformTransactionManager"),
    VEROMODA("veromodaJdbcTemplate", "veromodaPlatformTransactionManager"),
    BESTSELLER("bestsellerJdbcTemplate", "bestsellerPlatformTransactionManager"),
    NAMEIT("nameitJdbcTemplate", "nameitPlatformTransactionManager"),
    JLINDEBERG("jlindebergJdbcTemplate", "jlindebergPlatformTransactionManager"),
    FOL("folJdbcTemplate", "folPlatformTransactionManager"),
    FORDUMP("fordumpJdbcTemplate", "fordumpPlatformTransactionManager"),
    HYBRIS("hybrisJdbcTemplate", "hybrisPlatformTransactionManager"),
    BI("biJdbcTemplate", "biPlatformTransactionManager");

    public String jdbcTemplateName;
    public String tranManagerName;

    SelectDataSourceType(String jdbcTemplateName, String tranManagerName) {
        this.jdbcTemplateName = jdbcTemplateName;
        this.tranManagerName = tranManagerName;
    }

    public String getJdbcTemplateName() {
        return jdbcTemplateName;
    }

    public void setJdbcTemplateName(String jdbcTemplateName) {
        this.jdbcTemplateName = jdbcTemplateName;
    }

    public String getTranManagerName() {
        return tranManagerName;
    }

    public void setTranManagerName(String tranManagerName) {
        this.tranManagerName = tranManagerName;
    }

    public static String getSelectJdbcTemplateNameType(String name) {
        if (name.isEmpty()) {
            return "";
        }

        SelectDataSourceType[] values = SelectDataSourceType.values();

        for (SelectDataSourceType selectDataSourceType : values) {
            String templateName = selectDataSourceType.name();
            if (name.equalsIgnoreCase(templateName)) {
                return selectDataSourceType.jdbcTemplateName;
            }
        }

        return "";
    }

    public static String getSelectTranManagerType(String name) {
        if (name.isEmpty()) {
            return "";
        }

        SelectDataSourceType[] values = SelectDataSourceType.values();

        for (SelectDataSourceType selectDataSourceType : values) {
            if (name.equalsIgnoreCase(selectDataSourceType.name())) {
                return selectDataSourceType.tranManagerName;
            }
        }

        return "";
    }

    public static String getSelectTranManagerTypeByJdbcName(String jdbcTemplateName) {
        if (jdbcTemplateName.isEmpty()) {
            return "";
        }

        SelectDataSourceType[] values = SelectDataSourceType.values();

        for (SelectDataSourceType selectDataSourceType : values) {
            if (jdbcTemplateName.equalsIgnoreCase(selectDataSourceType.getJdbcTemplateName())) {
                return selectDataSourceType.tranManagerName;
            }
        }

        return "";
    }

    public static void main(String[] args) {
        System.out.println(getSelectJdbcTemplateNameType("only"));
        System.out.println(getSelectTranManagerType("only"));
        System.out.println(getSelectTranManagerTypeByJdbcName("onlyLzszJdbcTemplate"));
    }

}
