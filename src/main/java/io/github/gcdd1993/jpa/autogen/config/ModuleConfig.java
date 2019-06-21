package io.github.gcdd1993.jpa.autogen.config;

import lombok.Data;

/**
 * 模块名称
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
@Data
public class ModuleConfig {
    private String packageName;
    private String classNameSuffix;
    private String ftlName;
    private String savePath;
}
