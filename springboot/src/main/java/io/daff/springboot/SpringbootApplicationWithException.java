package io.daff.springboot;

import io.daff.springboot.exception.AException;
import io.daff.springboot.exception.BException;
import io.daff.springboot.exception.CException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.daff.springboot.mapper")
public class SpringbootApplicationWithException {

    public static void main(String[] args) {
        try {
            throw new CException(new BException(new AException(new Exception("test"))));
        } catch (Throwable t) {
            while (t != null) {
                System.out.println(t.getClass());
                t = t.getCause();
            }
        }
    }

}
