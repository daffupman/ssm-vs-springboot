package io.daff.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
class StopWatchTests {

    @Test
    public void testHello() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("task1");
        Thread.sleep(3000L);
        stopWatch.stop();

        stopWatch.start("task2");
        Thread.sleep(2000L);
        stopWatch.stop();

        stopWatch.start("task3");
        Thread.sleep(1000L);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

}
