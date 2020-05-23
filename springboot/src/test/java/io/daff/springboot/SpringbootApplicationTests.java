package io.daff.springboot;

import io.daff.springboot.event.WeatherRunListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private WeatherRunListener listener;

    @Test
    void contextLoads() {
    }

    @Test
    public void testEvent() {
        listener.rain();
        listener.snow();
    }

}
