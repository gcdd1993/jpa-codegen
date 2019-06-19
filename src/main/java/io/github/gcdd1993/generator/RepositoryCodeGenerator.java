package io.github.gcdd1993.generator;

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
        EntityInfo entityInfo = applicationContext.getAttribute("entityInfo", EntityInfo.class);

        String packageName = applicationContext.getOrDefaultAttribute("repository.package", entityInfo.getPackageName() + ".repository", String.class);
        applicationContext.setAttribute("packageName", packageName);

        // targetFileName
        applicationContext.setAttribute("targetClassName", entityInfo.getSimpleName() + applicationContext.getAttribute("repository.suffix"));
        applicationContext.setAttribute("templateName", applicationContext.getAttribute("repository.template"));

    }

    @Override
    public void afterGenerate() {
        EntityInfo entityInfo = applicationContext.getAttribute("entityInfo", EntityInfo.class);
        String packageName = applicationContext.getAttribute("packageName");
        String targetClassName = applicationContext.getAttribute("targetClassName");

        this.applicationContext.setAttribute("repository_simple_name_" + entityInfo.getSimpleName(), targetClassName);
        this.applicationContext.setAttribute("repository_full_name_" + entityInfo.getSimpleName(), packageName + "." + targetClassName);
    }

}
