import io.github.gcdd1993.context.ApplicationContext;
import io.github.gcdd1993.context.DefaultApplicationContext;
import io.github.gcdd1993.generator.ICodeGenerator;
import io.github.gcdd1993.generator.RepositoryCodeGenerator;
import io.github.gcdd1993.generator.ServiceCodeGenerator;
import org.junit.Test;

import java.io.IOException;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/19.
 */
public class ApplicationTest {

    @Test
    public void generate() throws IOException {
        String configFile = "codegen.conf";

        ApplicationContext applicationContext = new DefaultApplicationContext(ApplicationTest.class.getResourceAsStream("/codegen.properties"));

        ICodeGenerator repositoryCodeGenerator = new RepositoryCodeGenerator(applicationContext);
        ICodeGenerator serviceCodeGenerator = new ServiceCodeGenerator(applicationContext);

        repositoryCodeGenerator.generate();
        serviceCodeGenerator.generate();

    }
}
