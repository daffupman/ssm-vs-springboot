package io.daff.springboot.event.event;

/**
 * @author daffupman
 * @since 2020/5/22
 */
public class SnowEvent extends WeatherEvent {

    @Override
    public String getWeather() {
        return "snow";
    }
}
