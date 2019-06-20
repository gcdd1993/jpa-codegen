package io.github.gcdd1993.jpa.autogen.render;

import io.github.gcdd1993.jpa.autogen.config.CodeGeneratorConfig;
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
public abstract class BaseRender implements IRender {

    protected final CodeGeneratorConfig config;

    protected Map<String, RenderingResponse> renderingResponseMap = new HashMap<>(256);

    protected BaseRender(CodeGeneratorConfig config) {
        this.config = config;
    }

    @Override
    public final RenderingResponse render(EntityInfo entityInfo) {
        RenderingRequest renderingRequest = beforeRender(entityInfo);
        renderingRequest.setAuthor(config.getAuthor());
        renderingRequest.setComments(config.getComments());
        renderingRequest.setDate(config.getDate());

        // use freemarker to render code.
        RenderingResponse renderingResponse = FreeMarkerUtils.process(renderingRequest);

        afterRender(renderingRequest, renderingResponse);
        return renderingResponse;
    }

    /**
     * 渲染前处理，主要是设置模板参数
     *
     * @param entityInfo 实体信息
     * @return 渲染请求参数
     */
    public abstract RenderingRequest beforeRender(EntityInfo entityInfo);

    /**
     * 渲染后处理
     *
     * @param renderingRequest  渲染请求参数
     * @param renderingResponse 渲染结果
     */
    public abstract void afterRender(RenderingRequest renderingRequest, RenderingResponse renderingResponse);
}
