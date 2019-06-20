package io.github.gcdd1993.jpa.autogen.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import io.github.gcdd1993.jpa.autogen.render.RenderingRequest;
import io.github.gcdd1993.jpa.autogen.render.RenderingResponse;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
@UtilityClass
public class FreeMarkerUtils {

    public static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setObjectWrapper(Configuration.getDefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        configuration.setDefaultEncoding("UTF-8");
    }

    /**
     * 获取解析后的值.
     *
     * @param renderingRequest 模板参数
     */
    public static RenderingResponse process(RenderingRequest renderingRequest) {
        FileWriter writer = null;
        RenderingResponse response = new RenderingResponse();
        response.setClassName(renderingRequest.getClassName());
        response.setPackageName(renderingRequest.getPackageName());
        response.setFtlName(renderingRequest.getFtlName());
        try {
            File file = checkFile(renderingRequest);
            if (file != null) {
                writer = new FileWriter(file);
                Template template = configuration.getTemplate(renderingRequest.getFtlName());
                template.process(renderingRequest, writer);
            }
            response.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            response.setSuccess(false);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    /**
     * 校验目标文件
     *
     * @return 目标文件
     */
    private static File checkFile(RenderingRequest renderingRequest) {
        // 创建文件夹
        String savePath = renderingRequest.getSavePath();
        String fileName = renderingRequest.getClassName() + ".java";
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String finalFileName = savePath + fileName;
        File nFile = new File(finalFileName);
        if (nFile.exists() && !renderingRequest.isCover()) {
            System.out.println("File \'" + finalFileName + "\' already exists, Skipped.");
            return null;
        } else {
            return nFile;
        }
    }

}
