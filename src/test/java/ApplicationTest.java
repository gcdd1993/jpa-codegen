import io.github.gcdd1993.jpa.autogen.CodeGenerator;
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

        new CodeGenerator("/example.properties")
                .registerRender("form")
                .registerRender("repository")
                .registerRender("service")
                .registerRender("controller")
                .generate();

    }
}
