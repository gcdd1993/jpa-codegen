package io.github.gcdd1993.jpa.codegen.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import io.github.gcdd1993.jpa.codegen.exception.JpaCodegenException;
import io.github.gcdd1993.jpa.codegen.render.RenderingRequest;
import io.github.gcdd1993.jpa.codegen.render.RenderingResponse;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
@Slf4j
@UtilityClass
public class FreeMarkerUtils {

    private static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setObjectWrapper(Configuration.getDefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        configuration.setDefaultEncoding("UTF-8");
    }

    /**
     * 获取解析后的字符串
     *
     * @param renderingRequest 模板参数
     * @return 渲染结果
     */
    private static String render(RenderingRequest renderingRequest) {
        try (Writer writer = new StringWriter()) {
            configuration.setDirectoryForTemplateLoading(new File(renderingRequest.getFtlPath()));
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setObjectWrapper(new BeansWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
            configuration.setWhitespaceStripping(true);

            Template template = configuration.getTemplate(renderingRequest.getFtlName());
            template.process(renderingRequest, writer);
            return writer.toString();
        } catch (TemplateException | IOException e) {
            throw new JpaCodegenException(String.format("render %s code source error.", renderingRequest.getEntity().getClassName()), e);
        }
    }

    /**
     * 获取解析后的值.
     *
     * @param renderingRequest 模板参数
     * @return 渲染结果
     */
    public static RenderingResponse process(RenderingRequest renderingRequest) {
        RenderingResponse response = new RenderingResponse();
        response.setClassName(renderingRequest.getClassName());
        response.setPackageName(renderingRequest.getPackageName());
        response.setFtlName(renderingRequest.getFtlName());

        String code = render(renderingRequest);
        try {
            if (code != null) {
                saveToFile(code, renderingRequest.getSavePath(), renderingRequest.getClassName() + ".java", renderingRequest.isCover());
            }
            response.setSuccess(true);
        } catch (IOException e) {
            throw new JpaCodegenException(String.format("render %s code source failed.", renderingRequest.getEntity().getClassName()), e);
        }
        return response;
    }

    private static void saveToFile(String code, String filePath, String fileName, boolean cover) throws IOException {
        String finalFileName = filePath + fileName;
        Path path = Paths.get(finalFileName);
        if (Files.exists(path)) {
            // check for cove
            if (!cover) {
                log.info("skip {} due to file exists.", fileName);
                return;
            } else {
                Files.delete(path);
            }
        }

        // check for dir
        Path dirPath = Paths.get(filePath);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        Files.createFile(path);
        Files.write(path, code.getBytes());

        log.debug("path: {}, code: {}", path, code);
    }

}
