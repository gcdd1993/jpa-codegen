package io.github.gcdd1993.jpa.autogen.generator;

import io.github.gcdd1993.jpa.autogen.constant.AttributeKey;
import io.github.gcdd1993.jpa.autogen.context.ApplicationContext;
import io.github.gcdd1993.jpa.autogen.model.EntityInfo;

/**
 * Form 表单代码生成器
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
public class FormCodeGenerator extends BaseCodeGenerator {

    private static final String DEFAULT_PACKAGE_NAME_SUFFIX = ".form";

    public FormCodeGenerator(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public void beforeGenerate() {
        EntityInfo entityInfo = applicationContext.getAttribute(AttributeKey.ENTITY_INFO, EntityInfo.class);

        String packageName = applicationContext.getOrDefaultAttribute(AttributeKey.FORM_PACKAGE, entityInfo.getPackageName() + DEFAULT_PACKAGE_NAME_SUFFIX, String.class);
        applicationContext.setAttribute(AttributeKey.PACKAGE_NAME, packageName);

        // targetFileName
        applicationContext.setAttribute(AttributeKey.TARGET_CLASS_NAME, entityInfo.getSimpleName() + applicationContext.getAttribute(AttributeKey.FORM_SUFFIX));
        applicationContext.setAttribute(AttributeKey.TEMPLATE_NAME, applicationContext.getAttribute(AttributeKey.FORM_TEMPLATE));
    }

    @Override
    public void afterGenerate() {

    }
}
