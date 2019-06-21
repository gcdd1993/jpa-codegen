package io.github.gcdd1993.jpa.codegen.util;

import freemarker.cache.StringTemplateLoader;
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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

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
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        String source;
        try (InputStream is = ResourceReader.getResourceAsStream(renderingRequest.getFtlName());
             BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
             Writer writer = new StringWriter()) {
            source = buffer.lines().collect(Collectors.joining("\n"));
            templateLoader.putTemplate("template", source);
            configuration.setTemplateLoader(templateLoader);
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setObjectWrapper(new BeansWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
            configuration.setWhitespaceStripping(true);

            Template template = configuration.getTemplate("template");
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
                saveToFile(code, renderingRequest.getSavePath(), renderingRequest.isCover());
            }
            response.setSuccess(true);
        } catch (IOException e) {
            throw new JpaCodegenException(String.format("render %s code source failed.", renderingRequest.getEntity().getClassName()), e);
        }
        return response;
    }

    private static void saveToFile(String code, String filePath, boolean cover) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.exists(path) && !cover) {
            return;
        }
        Files.createFile(path);
        Files.write(path, code.getBytes());

        log.debug("path: {}, code: {}", path, code);
    }

}
