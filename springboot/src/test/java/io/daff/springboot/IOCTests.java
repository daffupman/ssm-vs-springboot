package io.daff.springboot;

import io.daff.springboot.ioc.anno.MyBeanImport;
import io.daff.springboot.ioc.xml.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@Import({MyBeanImport.class})
// @ContextConfiguration(locations = {"classpath:ioc/demo.xml"})
class IOCTests {

    @Autowired
    private HelloService helloService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testHello() {
        System.out.println(helloService.hi());
    }

}
