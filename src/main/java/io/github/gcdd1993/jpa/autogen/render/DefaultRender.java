package io.github.gcdd1993.jpa.autogen.render;

import io.github.gcdd1993.jpa.autogen.config.CodeGeneratorConfig;
import io.github.gcdd1993.jpa.autogen.config.ModuleConfig;
import io.github.gcdd1993.jpa.autogen.model.EntityInfo;
import io.github.gcdd1993.jpa.autogen.util.FreeMarkerUtils;

import java.util.HashMap;
import java.util.Map;

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
        renderingRequest.setClassName(entityInfo.getSimpleName() + moduleConfig.getClassNameSuffix());
        renderingRequest.setPackageName(moduleConfig.getPackageName());
        renderingRequest.setFtlName(moduleConfig.getFtlName());
        renderingRequest.setSavePath(moduleConfig.getSavePath());

        renderingRequest.setEntity(entityInfo);

        renderingRequest.setAuthor(config.getAuthor());
        renderingRequest.setComments(config.getComments());
        renderingRequest.setDate(config.getDate());

        // use freemarker to render code.
        RenderingResponse lastRenderingResponse = FreeMarkerUtils.process(renderingRequest);

        lastRenderingResponseMap.put(module, lastRenderingResponse);
        return lastRenderingResponse;
    }

}
