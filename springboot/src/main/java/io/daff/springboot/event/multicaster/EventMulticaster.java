package io.daff.springboot.event.multicaster;

import io.daff.springboot.event.event.WeatherEvent;
import io.daff.springboot.event.listener.WeatherListener;

import java.util.List;

/**
 * @author daffupman
 * @since 2020/5/22
 */
public interface EventMulticaster {

    /**
     * 广播事件监听器
     */
    void publish(WeatherEvent event);

    /**
     * 添加事件监听器
     */
    void addListener(WeatherListener listener);

    /**
     * 添加事件监听器
     */
    void registerListeners(List<WeatherListener> weatherListeners);

    /**
     * 移除事件监听器
     */
    void removeListener(WeatherListener listener);
}
