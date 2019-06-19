package io.github.gcdd1993.generator;

import io.github.gcdd1993.constant.AttributeKey;
import io.github.gcdd1993.context.ApplicationContext;
import io.github.gcdd1993.model.EntityInfo;

/**
 * Repository 代码生成器
 *
 * @author gaochen
 * Created on 2019/6/19.
 */
public class RepositoryCodeGenerator extends BaseCodeGenerator {

    public RepositoryCodeGenerator(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void beforeGenerate() {
        EntityInfo entityInfo = applicationContext.getAttribute(AttributeKey.ENTITY_INFO, EntityInfo.class);

        String packageName = applicationContext.getOrDefaultAttribute("repository.package", entityInfo.getPackageName() + ".repository", String.class);
        applicationContext.setAttribute(AttributeKey.PACKAGE_NAME, packageName);

        // targetFileName
        applicationContext.setAttribute(AttributeKey.TARGET_CLASS_NAME, entityInfo.getSimpleName() + applicationContext.getAttribute("repository.suffix"));
        applicationContext.setAttribute(AttributeKey.TEMPLATE_NAME, applicationContext.getAttribute("repository.template"));

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
