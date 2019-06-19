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
    String PARAMS = "params";
    String TEMPLATE_NAME = "template_name";

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

    String ENTITY_PACKAGE = "entity_package";
    String IMPORT_IGNORE_PACKAGE = "import_ignore_package";

}
