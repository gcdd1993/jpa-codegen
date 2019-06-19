package io.github.gcdd1993.jpa.autogen.constant;

/**
 * 模板中用到的参数名
 * 除了这些，也可以使用{@link AttributeKey}中定义的key，以及配置文件中定义的key,同样的'.'会转为'_'
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
public interface TemplateKey {
    String PACKAGE_NAME = "package_name";
    String IMPORTS = "imports";
    String CLASS_NAME = "class_name";
    String ENTITY = "entity";
    String DATE = "date";
    String COMMENTS = "comments";

    String REPOSITORY_SIMPLE_NAME = "repository_simple_name";
    String REPOSITORY_FULL_NAME = "repository_full_name";

    String SERVICE_SIMPLE_NAME = "service_simple_name";
    String SERVICE_FULL_NAME = "service_full_name";

}
