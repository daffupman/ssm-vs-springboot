package io.daff.springboot.event.multicaster;

import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/22
 */
@Component
public class WeatherEventMulticaster extends AbstractEventMulticaster {

    @Override
    void doStart() {
        System.out.println("begin broadcast weather event");
    }

    @Override
    void doEnd() {
        System.out.println("end broadcast weather event");
    }
}
