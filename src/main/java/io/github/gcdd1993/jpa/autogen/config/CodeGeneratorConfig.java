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
    private String entityFlag;
    private String ftlPath;
    private String author;
    private String date;
    private String comments;
    private boolean cover;

    private Map<String, ModuleConfig> moduleConfigMap = new HashMap<>();

    private Map<String, String> otherParams;
}
