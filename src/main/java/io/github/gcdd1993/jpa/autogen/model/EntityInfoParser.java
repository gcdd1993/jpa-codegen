package io.github.gcdd1993.jpa.autogen.model;

import io.github.gcdd1993.jpa.autogen.util.ReflectUtils;
import lombok.experimental.UtilityClass;

import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
@UtilityClass
public class EntityInfoParser {

    /**
     * 通过反射获取实体类属性
     *
     * @param clazz 实体类
     * @return 实体信息
     */
    public static EntityInfo parse(Class<?> clazz) {
        EntityInfo entityInfo = new EntityInfo();
        // 主键
        Field field = ReflectUtils.getFieldByAnnotation(clazz, Id.class)
                .orElse(ReflectUtils.getFieldByAnnotation(clazz, org.springframework.data.annotation.Id.class)
                        .orElse(null));
        if (field == null) {
            return null;
        }
        entityInfo.setIdClassName(field.getType().getSimpleName());
        entityInfo.setIdPackageName(field.getType().getPackage().getName());

        entityInfo.setClassName(clazz.getSimpleName());
        entityInfo.setPackageName(clazz.getPackage().getName());

        List<EntityInfo.FieldItem> fields = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            Package packageType = f.getType().getPackage();
            boolean javaLangType = packageType == null || "java.lang".equals(packageType.getName());
            Id idAnnotation = f.getAnnotation(Id.class);
            if (javaLangType && idAnnotation == null) {
                EntityInfo.FieldItem fieldItem = new EntityInfo.FieldItem();
                fieldItem.setClassName(f.getType().getSimpleName());
                fieldItem.setName(f.getName());

                // 字段注解
                Annotation[] fieldAnnotations = f.getAnnotations();
                if (fieldAnnotations.length > 0) {
                    List<String> annotationTypes = new ArrayList<>();
                    List<String> annotations = new ArrayList<>();

                    for (Annotation annotation : fieldAnnotations) {
                        annotations.add(annotation.annotationType().getSimpleName());
                        annotationTypes.add(annotation.annotationType().getPackage().getName());
                    }
                    fieldItem.setAnnotations(annotations);
                    fieldItem.setAnnotations(annotationTypes);
                }

                fields.add(fieldItem);
            }
        }

        entityInfo.setFields(fields);
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        if (tableAnnotation != null) {
            entityInfo.setTableName(tableAnnotation.name());
        }

        return entityInfo;

    }
}
