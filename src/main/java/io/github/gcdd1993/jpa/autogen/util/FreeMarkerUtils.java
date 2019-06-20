package io.github.gcdd1993.jpa.autogen.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.utility.StringUtil;

import java.io.*;
import java.util.Map;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
public class FreeMarkerUtils {

    public static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setObjectWrapper(Configuration.getDefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(StringUtil.class, "/");
    }

    /**
     * 获取解析后的值.
     *
     * @param params  模板参数
     * @param ftlName 模板路径
     */
    public static void process(Map<String, Object> params, String ftlName, File savePath) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(savePath);
            Template template = configuration.getTemplate(ftlName);
            template.process(params, writer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
