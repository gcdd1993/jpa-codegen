package io.github.gcdd1993.jpa.codegen.render;

import lombok.Data;

/**
 * 渲染结果
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
@Data
public class RenderingResponse {
    private boolean success;
    private String errorMsg;
    private String ftlName;
    private String packageName;
    private String className;
}
