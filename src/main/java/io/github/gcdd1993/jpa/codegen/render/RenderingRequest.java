package io.github.gcdd1993.jpa.codegen.render;

import io.github.gcdd1993.jpa.codegen.metadata.EntityInfo;
import lombok.Data;

import java.util.Map;

/**
 * 渲染请求
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
@Data
public class RenderingRequest {
    private String ftlName;
    private String savePath;
    private String packageName;
    private boolean cover;
    private String className;
    private String author;
    private String date;
    private String comments;
    private EntityInfo entity;
    private Map<String, RenderingResponse> lastRenderResponse;
    private Map<String, String> otherParams;
}
