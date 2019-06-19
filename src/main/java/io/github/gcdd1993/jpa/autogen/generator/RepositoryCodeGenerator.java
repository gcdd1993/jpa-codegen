package io.github.gcdd1993.jpa.autogen.generator;

import io.github.gcdd1993.jpa.autogen.constant.AttributeKey;
import io.github.gcdd1993.jpa.autogen.context.ApplicationContext;
import io.github.gcdd1993.jpa.autogen.model.EntityInfo;

/**
 * Repository 代码生成器
 *
 * @author gaochen
 * Created on 2019/6/19.
 */
public class RepositoryCodeGenerator extends BaseCodeGenerator {

    private static final String DEFAULT_PACKAGE_NAME_SUFFIX = ".service";

    public RepositoryCodeGenerator(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void beforeGenerate() {
        EntityInfo entityInfo = applicationContext.getAttribute(AttributeKey.ENTITY_INFO, EntityInfo.class);

        String packageName = applicationContext.getOrDefaultAttribute(AttributeKey.REPOSITORY_PACKAGE, entityInfo.getPackageName() + DEFAULT_PACKAGE_NAME_SUFFIX, String.class);
        applicationContext.setAttribute(AttributeKey.PACKAGE_NAME, packageName);

        // targetFileName
        applicationContext.setAttribute(AttributeKey.TARGET_CLASS_NAME, entityInfo.getSimpleName() + applicationContext.getAttribute(AttributeKey.REPOSITORY_SUFFIX));
        applicationContext.setAttribute(AttributeKey.TEMPLATE_NAME, applicationContext.getAttribute(AttributeKey.REPOSITORY_TEMPLATE));

    }

    @Override
    public void afterGenerate() {
        EntityInfo entityInfo = applicationContext.getAttribute(AttributeKey.ENTITY_INFO, EntityInfo.class);
        String packageName = applicationContext.getAttribute(AttributeKey.PACKAGE_NAME);
        String targetClassName = applicationContext.getAttribute(AttributeKey.TARGET_CLASS_NAME);

        this.applicationContext.setAttribute(AttributeKey.REPOSITORY_SIMPLE_NAME_PREFIX + entityInfo.getSimpleName(), targetClassName);
        this.applicationContext.setAttribute(AttributeKey.REPOSITORY_FULL_NAME_PREFIX + entityInfo.getSimpleName(), packageName + "." + targetClassName);
    }

}
