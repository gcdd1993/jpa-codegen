package io.github.gcdd1993.jpa.autogen.constant;

/**
 * 默认的全局变量
 * 自定义配置中的'.'会转为'_',目的是解决Freemarker参数的'.'会识别为对象访问属性
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
public interface AttributeKey {
    String TEMPLATE_PATH = "template_path";
    String PACKAGE_NAME = "package_name";
    String TARGET_CLASS_NAME = "target_class_name";
    String FORCE_OVERRIDE = "force_override";
    String ENTITY_INFO = "entity_info";
    String ENTITY_INFOS = "entity_infos";
    String TEMPLATE_NAME = "template_name";
    String DATE = "date";
    String IMPORTS = "imports";
    String SIMPLE_CLASS_NAME = "simple_class_name";
    String FULL_CLASS_NAME = "full_class_name";

    String REPOSITORY_SIMPLE_NAME_PREFIX = "repository_simple_name_";
    String REPOSITORY_FULL_NAME_PREFIX = "repository_full_name_";
    String REPOSITORY_PACKAGE = "repository_package";
    String REPOSITORY_SUFFIX = "repository_suffix";
    String REPOSITORY_TEMPLATE = "repository_template";

    String SERVICE_SIMPLE_NAME_PREFIX = "service_simple_name_";
    String SERVICE_FULL_NAME_PREFIX = "service_full_name_";
    String SERVICE_PACKAGE = "service_package";
    String SERVICE_SUFFIX = "service_suffix";
    String SERVICE_TEMPLATE = "service_template";

    String FORM_SIMPLE_NAME_PREFIX = "form_simple_name_";
    String FORM_FULL_NAME_PREFIX = "form_full_name_";
    String FORM_PACKAGE = "form_package";
    String FORM_SUFFIX = "form_suffix";
    String FORM_TEMPLATE = "form_template";

    String CONTROLLER_SIMPLE_NAME_PREFIX = "controller_simple_name_";
    String CONTROLLER_FULL_NAME_PREFIX = "controller_full_name_";
    String CONTROLLER_PACKAGE = "controller_package";
    String CONTROLLER_SUFFIX = "controller_suffix";
    String CONTROLLER_TEMPLATE = "controller_template";

    String ENTITY_PACKAGE = "entity_package";
    String IMPORT_IGNORE_PACKAGE = "import_ignore_package";

}
