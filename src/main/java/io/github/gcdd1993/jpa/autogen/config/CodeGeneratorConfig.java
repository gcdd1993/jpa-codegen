package io.github.gcdd1993.jpa.autogen.config;

import lombok.Data;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
@Data
public class CodeGeneratorConfig {
    private String entityPackage;
    private String ftlPath;
    private String author;
    private String date;
    private String comments;
    private boolean forceOverride;

    private ModuleConfig repository;
    private ModuleConfig service;
    private ModuleConfig form;
    private ModuleConfig controller;
}
