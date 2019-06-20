package io.github.gcdd1993.jpa.autogen.render;

import io.github.gcdd1993.jpa.autogen.config.CodeGeneratorConfig;
import io.github.gcdd1993.jpa.autogen.config.ModuleConfig;
import io.github.gcdd1993.jpa.autogen.model.EntityInfo;

/**
 * Repository 渲染器
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
public class RepositoryRender extends BaseRender {

    public RepositoryRender(CodeGeneratorConfig config) {
        super(config);
    }

    @Override
    public RenderingRequest beforeRender(EntityInfo entityInfo) {
        RenderingRequest renderingRequest = new RenderingRequest();

        ModuleConfig moduleConfig = config.getRepository();
        renderingRequest.setClassName(entityInfo.getSimpleName() + moduleConfig.getClassNameSuffix());
        renderingRequest.setPackageName(moduleConfig.getPackageName());
        renderingRequest.setFtlName(moduleConfig.getFtlName());
        renderingRequest.setSavePath(moduleConfig.getSavePath());

        renderingRequest.setEntity(entityInfo);
        return renderingRequest;
    }

    @Override
    public void afterRender(RenderingRequest renderingRequest, RenderingResponse renderingResponse) {
        // put render response to map for service or other usage.
        renderingResponseMap.put(renderingRequest.getEntity().getSimpleName() + "_repository", renderingResponse);
    }
}
