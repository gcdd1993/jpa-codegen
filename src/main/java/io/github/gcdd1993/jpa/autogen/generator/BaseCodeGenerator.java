package io.github.gcdd1993.jpa.autogen.generator;

import io.github.gcdd1993.jpa.autogen.constant.AttributeKey;
import io.github.gcdd1993.jpa.autogen.context.ApplicationContext;
import io.github.gcdd1993.jpa.autogen.model.EntityInfo;
import io.github.gcdd1993.jpa.autogen.util.FreeMarkerUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
public abstract class BaseCodeGenerator implements ICodeGenerator {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    protected ApplicationContext applicationContext;

    protected ICodeGenerator nextCodeGenerator;

    public BaseCodeGenerator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 解析文件存放位置
     *
     * @return 文件存放位置
     */
    private String parseTargetPath() {
        return "src/main/java/" + applicationContext.getAttribute(AttributeKey.PACKAGE_NAME).replace(".", "/") + "/";
    }

    /**
     * 校验目标文件
     *
     * @return 目标文件
     */
    private File checkFile() {
        // 创建文件夹
        String targetPath = parseTargetPath();
        File dir = new File(targetPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String finalFileName = targetPath + applicationContext.getAttribute(AttributeKey.TARGET_CLASS_NAME) + ".java";
        File nFile = new File(finalFileName);
        if (nFile.exists() && !applicationContext.getAttribute(AttributeKey.FORCE_OVERRIDE, Boolean.class)) {
            System.out.println("File \'" + finalFileName + "\' already exists, Skipped.");
            return null;
        } else {
            return nFile;
        }
    }

    @SuppressWarnings("unchecked")
    public void generate(EntityInfo entityInfo) {
        applicationContext.setAttribute(AttributeKey.ENTITY_INFO, entityInfo);
        // 设置公用模板参数
        applicationContext.setAttribute(AttributeKey.DATE, DATE_TIME_FORMATTER.format(LocalDate.now()));

        // imports
        String idClassPackage = entityInfo.getIdClass().getPackage().getName();
        String importIgnorePackage = applicationContext.getAttribute(AttributeKey.IMPORT_IGNORE_PACKAGE);

        if (!importIgnorePackage.contains(idClassPackage) && !applicationContext.getAttribute(AttributeKey.ENTITY_PACKAGE).equals(idClassPackage)) {
            applicationContext.setAttribute(AttributeKey.IMPORTS, Collections.singletonList(entityInfo.getIdClass().getName()));
        }

        beforeGenerate();

        // put all attributes into params
        Map<String, Object> params = new HashMap<>(256);
        applicationContext.getAttributes().forEach(params::put);

        FreeMarkerUtils.process(params, parseTargetPath(),checkFile());

        afterGenerate();

        if (nextCodeGenerator != null) {
            nextCodeGenerator.generate();
        } else {
            System.out.println(String.format("entity %s generate done.", entityInfo.getSimpleName()));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void generate() {
        List<EntityInfo> entityInfos = applicationContext.getAttribute(AttributeKey.ENTITY_INFOS, List.class);

        entityInfos.forEach(this::generate);
    }

    /**
     * 处理前回调
     */
    public abstract void beforeGenerate();

    /**
     * 处理后回调
     */
    public abstract void afterGenerate();

}
