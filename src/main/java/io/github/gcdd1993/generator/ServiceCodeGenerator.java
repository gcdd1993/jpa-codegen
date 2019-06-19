package io.github.gcdd1993.generator;

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

    public ServiceCodeGenerator(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void beforeGenerate() {
        EntityInfo entityInfo = applicationContext.getAttribute("entityInfo", EntityInfo.class);

        String packageName = applicationContext.getOrDefaultAttribute("service.package",
                entityInfo.getPackageName() + ".service",
                String.class);
        applicationContext.setAttribute("packageName", packageName);

        // targetFileName
        applicationContext.setAttribute("targetClassName", entityInfo.getSimpleName() + applicationContext.getAttribute("service.suffix"));
        applicationContext.setAttribute("templateName", applicationContext.getAttribute("service.template"));

        // import repository
        Map<String, Object> params = applicationContext.getAttribute("params", Map.class);

        params.put("repository_simple_name", this.applicationContext.getAttribute("repository_simple_name_" + entityInfo.getSimpleName()));
        params.put("repository_full_name", this.applicationContext.getAttribute("repository_full_name_" + entityInfo.getSimpleName()));
    }

    @Override
    public void afterGenerate() {
        EntityInfo entityInfo = applicationContext.getAttribute("entityInfo", EntityInfo.class);
        String packageName = applicationContext.getAttribute("packageName");
        String targetClassName = applicationContext.getAttribute("targetClassName");

        this.applicationContext.setAttribute("service_simple_name_" + entityInfo.getSimpleName(), targetClassName);
        this.applicationContext.setAttribute("service_full_name_" + entityInfo.getSimpleName(), packageName + "." + targetClassName);
    }

}
