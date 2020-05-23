package io.daff.springboot.event.listener;

import io.daff.springboot.event.event.WeatherEvent;

/**
 * @author daffupman
 * @since 2020/5/22
 */
public interface WeatherListener {

    void onWeatherEvent(WeatherEvent event);
}
