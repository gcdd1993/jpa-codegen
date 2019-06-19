package io.github.gcdd1993.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.gcdd1993.context.ApplicationContext;
import io.github.gcdd1993.model.EntityInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
public abstract class BaseCodeGenerator implements ICodeGenerator {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    protected ApplicationContext applicationContext;

    public BaseCodeGenerator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

        this.applicationContext.setAttribute("templatePath", applicationContext.getAttribute("template.basePath"));
    }


    /**
     * 解析文件存放位置
     *
     * @return 文件存放位置
     */
    private String parseTargetPath() {
        return "src/main/java/" + applicationContext.getAttribute("packageName").replace(".", "/") + "/";
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
        String finalFileName = targetPath + applicationContext.getAttribute("targetClassName") + ".java";
        File nFile = new File(finalFileName);
        if (nFile.exists() && !applicationContext.getAttribute("forceOverride", Boolean.class)) {
            System.out.println("File \'" + finalFileName + "\' already exists, Skipped.");
            return null;
        } else {
            return nFile;
        }
    }

    @SuppressWarnings("unchecked")
    public void generate(EntityInfo entityInfo) {
        applicationContext.setAttribute("entityInfo", entityInfo);
        applicationContext.setAttribute("params", new ConcurrentHashMap<>(256));
        // 设置公用模板参数
        Map<String, Object> params = applicationContext.getAttribute("params", Map.class);
        params.put("entity", entityInfo);

        params.put("date", DATE_TIME_FORMATTER.format(LocalDate.now()));

        // put all config into params
        applicationContext.getAttributes()
                .forEach((k, v) -> params.put(k.replace(".", "_"), v));

        // imports
        String idClassPackage = entityInfo.getIdClass().getPackage().getName();
        String importIgnorePackage = applicationContext.getAttribute("import.ignore.package");

        if (!importIgnorePackage.contains(idClassPackage) && !applicationContext.getAttribute("entity.package").equals(idClassPackage)) {
            params.put("imports", Collections.singletonList(entityInfo.getIdClass().getName()));
        }

        beforeGenerate();

        File file = checkFile();
        if (file == null) {
            return;
        }

        Writer writer = null;
        try {
            writer = new FileWriter(file);

            Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            Template template = configuration.getTemplate(applicationContext.getAttribute("templatePath") +
                    applicationContext.getAttribute("templateName"));

            template.process(params, writer);

            afterGenerate();
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理前回调
     */
    public abstract void beforeGenerate();

    /**
     * 处理后回调
     */
    public abstract void afterGenerate();

    @SuppressWarnings("unchecked")
    @Override
    public void generate() {
        List<EntityInfo> entityInfos = applicationContext.getAttribute("entityInfos", List.class);

        entityInfos.forEach(this::generate);
    }
}
