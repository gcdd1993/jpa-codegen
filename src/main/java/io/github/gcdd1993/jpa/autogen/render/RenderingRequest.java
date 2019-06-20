package io.github.gcdd1993.jpa.autogen.render;

import io.github.gcdd1993.jpa.autogen.model.EntityInfo;
import lombok.Data;

import java.util.List;
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
    private List<EntityInfo.FieldItem> fields;
    private EntityInfo entity;
    private Map<String, RenderingResponse> lastRenderResponse;
    private Map<String, String> otherParams;
}
