import io.github.gcdd1993.jpa.autogen.AutoGenTask;
import io.github.gcdd1993.jpa.autogen.generator.RepositoryCodeGenerator;
import io.github.gcdd1993.jpa.autogen.generator.ServiceCodeGenerator;
import org.junit.Test;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/19.
 */
public class ApplicationTest {

    @Test
    public void generate() {

        new AutoGenTask("/codegen.properties")
                .registerTask(RepositoryCodeGenerator.class)
                .registerTask(ServiceCodeGenerator.class)
                .run();

    }
}
