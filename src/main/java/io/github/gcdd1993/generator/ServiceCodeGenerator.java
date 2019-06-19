package io.github.gcdd1993.generator;

import io.github.gcdd1993.constant.AttributeKey;
import io.github.gcdd1993.constant.TemplateKey;
import io.github.gcdd1993.context.ApplicationContext;
import io.github.gcdd1993.model.EntityInfo;

import java.util.Map;

/**
 * Service 代码生成器
 *
 * @author gaochen
 * Created on 2019/6/19.
 */
public class ServiceCodeGenerator extends BaseCodeGenerator {

    private static final String DEFAULT_SERVICE_PACKAGE_NAME_SUFFIX = ".service";

    public ServiceCodeGenerator(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void beforeGenerate() {
        EntityInfo entityInfo = applicationContext.getAttribute(AttributeKey.ENTITY_INFO, EntityInfo.class);

        String packageName = applicationContext.getOrDefaultAttribute("service.package",
                entityInfo.getPackageName() + DEFAULT_SERVICE_PACKAGE_NAME_SUFFIX,
                String.class);
        applicationContext.setAttribute(AttributeKey.PACKAGE_NAME, packageName);

        // targetFileName
        applicationContext.setAttribute(AttributeKey.TARGET_CLASS_NAME, entityInfo.getSimpleName() + applicationContext.getAttribute("service.suffix"));
        applicationContext.setAttribute(AttributeKey.TEMPLATE_NAME, applicationContext.getAttribute("service.template"));

        // import repository
        Map<String, Object> params = applicationContext.getAttribute(AttributeKey.PARAMS, Map.class);

        params.put(TemplateKey.REPOSITORY_SIMPLE_NAME, this.applicationContext.getAttribute(AttributeKey.REPOSITORY_SIMPLE_NAME_PREFIX + entityInfo.getSimpleName()));
        params.put(TemplateKey.REPOSITORY_FULL_NAME, this.applicationContext.getAttribute(TemplateKey.REPOSITORY_FULL_NAME + entityInfo.getSimpleName()));
    }

    @Override
    public void afterGenerate() {
        EntityInfo entityInfo = applicationContext.getAttribute(AttributeKey.ENTITY_INFO, EntityInfo.class);
        String packageName = applicationContext.getAttribute(AttributeKey.PACKAGE_NAME);
        String targetClassName = applicationContext.getAttribute(AttributeKey.TARGET_CLASS_NAME);

        this.applicationContext.setAttribute(AttributeKey.SERVICE_SIMPLE_NAME_PREFIX + entityInfo.getSimpleName(), targetClassName);
        this.applicationContext.setAttribute(AttributeKey.SERVICE_FULL_NAME_PREFIX + entityInfo.getSimpleName(), packageName + "." + targetClassName);
    }

}
