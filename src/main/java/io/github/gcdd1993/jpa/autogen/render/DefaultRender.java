package io.github.gcdd1993.jpa.autogen.render;

import io.github.gcdd1993.jpa.autogen.config.CodeGeneratorConfig;
import io.github.gcdd1993.jpa.autogen.config.ModuleConfig;
import io.github.gcdd1993.jpa.autogen.metadata.EntityInfo;
import io.github.gcdd1993.jpa.autogen.util.FreeMarkerUtils;

import java.util.*;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
public class DefaultRender implements IRender {

    private final CodeGeneratorConfig config;

    private Map<String, RenderingResponse> lastRenderingResponseMap = new HashMap<>();

    public DefaultRender(CodeGeneratorConfig config) {
        this.config = config;
    }

    @Override
    public final RenderingResponse render(EntityInfo entityInfo, String module) {
        RenderingRequest renderingRequest = new RenderingRequest();

        renderingRequest.setLastRenderResponse(lastRenderingResponseMap);

        ModuleConfig moduleConfig = config.getModuleConfigMap().get(module);
        renderingRequest.setClassName(entityInfo.getClassName() + moduleConfig.getClassNameSuffix());
        if (moduleConfig.getPackageName() != null) {
            renderingRequest.setPackageName(moduleConfig.getPackageName());
            renderingRequest.setSavePath(moduleConfig.getSavePath());
        } else {
            String packageName = entityInfo.getPackageName().replace(config.getEntityFlag(), module);
            renderingRequest.setPackageName(packageName);
            renderingRequest.setSavePath("src/main/java/" + packageName.replace(".", "/") + "/");
        }
        renderingRequest.setFtlName(moduleConfig.getFtlName());
        renderingRequest.setCover(config.isCover());

        renderingRequest.setEntity(entityInfo);

        renderingRequest.setAuthor(config.getAuthor());
        renderingRequest.setComments(config.getComments());
        renderingRequest.setDate(config.getDate());

        // fields ，只支持基本类型映射
        renderingRequest.setFields(entityInfo.getFields());
        renderingRequest.setOtherParams(config.getOtherParams());

        // use freemarker to render code.
        RenderingResponse lastRenderingResponse = FreeMarkerUtils.process(renderingRequest);

        lastRenderingResponseMap.put(module, lastRenderingResponse);
        return lastRenderingResponse;
    }

}
