package io.daff.springboot.event.listener;

import io.daff.springboot.event.event.SnowEvent;
import io.daff.springboot.event.event.WeatherEvent;

/**
 * @author daffupman
 * @since 2020/5/22
 */
public class SnowListener implements WeatherListener {

    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if (event instanceof SnowEvent) {
            System.out.println("hello " + event.getWeather());
        }
    }
}
