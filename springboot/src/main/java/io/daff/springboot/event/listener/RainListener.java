package io.daff.springboot.event.listener;

import io.daff.springboot.event.event.RainEvent;
import io.daff.springboot.event.event.WeatherEvent;

/**
 * @author daffupman
 * @since 2020/5/22
 */
public class RainListener implements WeatherListener {

    @Override
    public void onWeatherEvent(WeatherEvent event) {
        if (event instanceof RainEvent) {
            System.out.println("hello " + event.getWeather());
        }
    }
}
