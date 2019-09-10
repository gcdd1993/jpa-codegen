package io.github.gcdd1993.jpa.codegen.render;

import io.github.gcdd1993.jpa.codegen.config.CodeGeneratorConfig;
import io.github.gcdd1993.jpa.codegen.config.ModuleConfig;
import io.github.gcdd1993.jpa.codegen.metadata.EntityInfo;
import io.github.gcdd1993.jpa.codegen.util.FreeMarkerUtils;

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
        String packageName = entityInfo.getPackageName().replace(entityInfo.getFlag(), moduleConfig.getFlag());

        renderingRequest.setPackageName(packageName);
        renderingRequest.setSavePath("src/main/java/" + packageName.replace(".", "/") + "/");
        renderingRequest.setFtlName(moduleConfig.getFtlName());

        renderingRequest.setFtlPath(config.getFtlPath());
        renderingRequest.setCover(config.isCover());

        renderingRequest.setEntity(entityInfo);

        renderingRequest.setAuthor(config.getAuthor());
        renderingRequest.setComments(config.getComments());
        renderingRequest.setDate(config.getDate());

        // fields ，只支持基本类型映射
        renderingRequest.setOtherParams(config.getOtherParams());

        // check for other imports
        renderingRequest.setImports(checkImports(entityInfo));

        // use freemarker to render code.
        RenderingResponse lastRenderingResponse = FreeMarkerUtils.process(renderingRequest);

        lastRenderingResponseMap.put(module, lastRenderingResponse);
        return lastRenderingResponse;
    }

    private List<String> checkImports(EntityInfo entityInfo) {
        List<String> imports = new ArrayList<>();
        String packageName = entityInfo.getId().getPackageName();
        if (!"java.lang".equals(packageName)) {
            imports.add(packageName);
        }
        return imports;
    }

}
