package io.github.gcdd1993.jpa.codegen.exception;

/**
 * exception when app running
 *
 * @author gaochen
 * Created on 2019/6/21.
 */
public class JpaCodegenException extends RuntimeException {
    public JpaCodegenException(String message) {
        super(message);
    }

    public JpaCodegenException(String message, Throwable cause) {
        super(message, cause);
    }
}
