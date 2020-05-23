package io.daff.springboot;

import io.daff.springboot.ioc.anno.MyBeanImport;
import io.daff.springboot.ioc.anno.Teacher;
import io.daff.springboot.ioc.xml.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({MyBeanImport.class})
class BeanTests {

    @Autowired
    private Teacher teacher;

    @Test
    void contextLoads() {
    }

    @Test
    public void testHello() {
        System.out.println(teacher.getName());
    }

}
