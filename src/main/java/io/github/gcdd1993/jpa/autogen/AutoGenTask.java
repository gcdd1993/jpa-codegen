package io.github.gcdd1993.jpa.autogen;

import io.github.gcdd1993.jpa.autogen.context.ApplicationContext;
import io.github.gcdd1993.jpa.autogen.context.DefaultApplicationContext;
import io.github.gcdd1993.jpa.autogen.generator.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
public class AutoGenTask {

    private ApplicationContext applicationContext;

    private LinkedList<ICodeGenerator> codeGeneratorList;

    public AutoGenTask(String configLocation) {
        try {
            applicationContext = new DefaultApplicationContext(configLocation);
        } catch (IOException e) {
            System.err.println("init application fail.");
        }
    }

    public AutoGenTask(InputStream inputStream) {
        try {
            applicationContext = new DefaultApplicationContext(inputStream);
        } catch (IOException e) {
            System.err.println("init application fail.");
        }
    }

    public AutoGenTask registerTask(Class<? extends ICodeGenerator> codeGeneratorClass) {
        try {
            ICodeGenerator codeGenerator = codeGeneratorClass.getConstructor(ApplicationContext.class).newInstance(applicationContext);
            codeGeneratorList.add(codeGenerator);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void run() {
        codeGeneratorList.forEach(ICodeGenerator::generate);
    }

    public void simpleRun() {
        this.registerTask(FormCodeGenerator.class)
                .registerTask(RepositoryCodeGenerator.class)
                .registerTask(ServiceCodeGenerator.class)
                .registerTask(ControllerCodeGenerator.class)
                .run();
    }

}
