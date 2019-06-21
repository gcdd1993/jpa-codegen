package io.github.gcdd1993.jpa.codegen.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/21.
 */
@Slf4j
@UtilityClass
public class ResourceReader {

    public static InputStream getResourceAsStream(String path) throws IOException {
        InputStream classPathResource = ResourceReader.class.getClassLoader().getResourceAsStream(path);
        if (classPathResource != null) {
            return classPathResource;
        }
        return new FileInputStream(new File(path));
    }

}
