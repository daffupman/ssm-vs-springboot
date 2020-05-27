package io.daff.springboot.exception;

import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.boot.SpringBootExceptionReporter;

/**
 * @author daffupman
 * @since 2020/5/27
 */
public class MyExceptionReporter implements SpringBootExceptionReporter {

    @Override
    public boolean reportException(Throwable failure) {
        if (failure instanceof UnsatisfiedDependencyException) {
            UnsatisfiedDependencyException exception = (UnsatisfiedDependencyException) failure;
            System.out.println("no such bean " + exception.getInjectionPoint().getField().getName());
        }
        return false;
    }
}
