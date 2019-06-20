package io.github.gcdd1993.jpa.autogen.config;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
@Data
public class CodeGeneratorConfig {
    private String entityPackage;
    private String basePackage;
    private String ftlPath;
    private String author;
    private String date;
    private String comments;
    private boolean forceOverride;

    private Map<String, ModuleConfig> moduleConfigMap = new HashMap<>();
}
