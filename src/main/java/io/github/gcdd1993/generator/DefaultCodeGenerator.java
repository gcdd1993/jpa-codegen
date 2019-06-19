package io.github.gcdd1993.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.gcdd1993.model.BaseTemplate;
import io.github.gcdd1993.model.EntityInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.List;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
public class DefaultCodeGenerator implements ICodeGenerator {

    /**
     * 解析模板参数
     */
    private void parseParams(EntityInfo entityInfo) {
        params.put("entity_package", entityInfo.getPackageName());
        params.put("entity_fullName", entityInfo.getFullName());
        params.put("entity_simpleName", entityInfo.getSimpleName());
        params.put("entity_id", entityInfo.getIdClass());

        params.put("date", DATE_TIME_FORMATTER.format(LocalDate.now()));

        params.put("author", ctx.getAttribute("author"));
        params.put("comments", ctx.getAttribute("comments"));

    }

    /**
     * 解析文件存放位置
     *
     * @return 文件存放位置
     */
    private String parseTargetPath(BaseTemplate baseTemplate) {
        return "src/main/java/" + baseTemplate.getPackageName().replace(".", "/") + "/";
    }

    /**
     * 校验目标文件
     *
     * @return 目标文件
     */
    private File checkFile(BaseTemplate baseTemplate) {
        String targetFileName =
        // 创建文件夹
        File dir = new File(parseTargetPath(baseTemplate));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File nFile = new File(parseTargetPath() + targetFileName);
        if (nFile.exists() && !forceOverride) {
            System.out.println("File \'" + parseTargetPath() + targetFileName + "\' already exists, Skipped.");
            return null;
        } else {
            return nFile;
        }
    }

    @Override
    public void generate(BaseTemplate baseTemplate) {
        EntityInfo entityInfo = baseTemplate.getEntityInfo();

        File file = checkFile();
        if (file == null) {
            return;
        }

        Writer writer = null;
        try {
            writer = new FileWriter(file);

            Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            Template template = configuration.getTemplate(baseTemplate.getTemplate());

            template.process(baseTemplate.toParams(), writer);
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
}
