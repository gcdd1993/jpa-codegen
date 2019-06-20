package io.github.gcdd1993.jpa.autogen.model;

import lombok.Data;

import java.util.List;

/**
 * 实体信息
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
@Data
public class EntityInfo {

    /**
     * 包名
     */
    private String packageName;
    /**
     * 实体类名
     */
    private String className;

    /**
     * 主键类名
     */
    private String idClassName;

    /**
     * 主键包名
     */
    private String idPackageName;

    /**
     * 所有< 属性名, 类 >
     */
    private List<FieldItem> fields;

    /**
     * 表名
     */
    private String tableName;

    @Data
    public static class FieldItem {
        private String className;
        private String name;
        private List<String> annotationTypes;
        private List<String> annotations;
    }

}
