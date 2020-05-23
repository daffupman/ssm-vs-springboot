package io.daff.springboot.event;

import io.daff.springboot.event.event.RainEvent;
import io.daff.springboot.event.event.SnowEvent;
import io.daff.springboot.event.multicaster.WeatherEventMulticaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/22
 */
@Component
public class WeatherRunListener {

    @Autowired
    private WeatherEventMulticaster multicaster;

    public void snow() {
        multicaster.publish(new SnowEvent());
    }

    public void rain() {
        multicaster.publish(new RainEvent());
    }
}
