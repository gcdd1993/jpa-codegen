package io.github.gcdd1993;

import io.github.gcdd1993.context.ApplicationContext;
import io.github.gcdd1993.context.SimpleApplicationContext;
import io.github.gcdd1993.generator.ICodeGenerator;
import io.github.gcdd1993.model.EntityInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
public class Application {

    private static final Map<String, Object> CONTENT = new HashMap<>(256);

    public static void main(String[] args) throws IOException {
        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setFullName("io.github.gcdd1993.entity.Person");
        entityInfo.setSimpleName("Person");
        entityInfo.setPackageName("io.github.gcdd1993.entity");
        entityInfo.setIdClass("Long");

        Properties properties = new Properties();
        InputStream resourceAsStream = Application.class.getResourceAsStream("/codegen.properties");
        properties.load(resourceAsStream);

        properties.getOrDefault()

        Enumeration<?> propertyNames = properties.propertyNames();
        ApplicationContext applicationContext = new SimpleApplicationContext();
        while (propertyNames.hasMoreElements()) {
            String key = (String) propertyNames.nextElement();
            applicationContext.setAttribute(key, properties.getProperty(key));
        }

        ICodeGenerator codeGenerator = new RepositoryCodeGenerator(applicationContext);
        codeGenerator.generate(entityInfo);
    }


}
