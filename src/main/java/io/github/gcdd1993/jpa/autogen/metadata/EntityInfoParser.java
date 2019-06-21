package io.github.gcdd1993.jpa.autogen.metadata;

import io.github.gcdd1993.jpa.autogen.util.ReflectUtils;
import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
@UtilityClass
public class EntityInfoParser {

    /**
     * 主键注解
     */
    private static final List<Class<? extends Annotation>> ID_CLASS_LIST = Arrays.asList(javax.persistence.Id.class, org.springframework.data.annotation.Id.class);

    /**
     * table注解
     */
    private static final Class<? extends Annotation> TABLE_CLASS = javax.persistence.Table.class;

    /**
     * 通过反射获取实体类属性
     *
     * @param clazz 实体类
     * @return 实体信息
     */
    public static EntityInfo parse(Class<?> clazz) {
        EntityInfo entityInfo = new EntityInfo();
        // 主键
        Field idField = null;
        for (Class<? extends Annotation> idClass : ID_CLASS_LIST) {
            Optional<Field> fieldByAnnotation = ReflectUtils.getFieldByAnnotation(clazz, idClass);
            if (fieldByAnnotation.isPresent()) {
                idField = fieldByAnnotation.get();
                break;
            }
        }
        if (idField == null) {
            return null;
        }
        entityInfo.setIdClassName(idField.getType().getSimpleName());
        entityInfo.setIdPackageName(idField.getType().getPackage().getName());


        entityInfo.setClassName(clazz.getSimpleName());
        entityInfo.setPackageName(clazz.getPackage().getName());

        List<EntityInfo.FieldItem> fields = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            Package packageType = f.getType().getPackage();
            boolean javaLangType = packageType == null || "java.lang".equals(packageType.getName());
            boolean isId = false;
            for (Class<? extends Annotation> idClass : ID_CLASS_LIST) {
                boolean hasAnnotation = ReflectUtils.hasAnnotation(f, idClassName);
                if (hasAnnotation) {
                    isId = true;
                    break;
                }
            }

            if (javaLangType && !isId) {
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
        Annotation tableAnnotation = ReflectUtils.getAnnotation(clazz, TABLE_CLASS_NAME);
        if (tableAnnotation != null) {
            entityInfo.setTableName(tableAnnotation.name());
        }

        return entityInfo;

    }
}
