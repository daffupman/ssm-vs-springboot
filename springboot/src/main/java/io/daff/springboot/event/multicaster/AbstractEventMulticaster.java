package io.daff.springboot.event.multicaster;

import io.daff.springboot.event.event.WeatherEvent;
import io.daff.springboot.event.listener.WeatherListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daffupman
 * @since 2020/5/22
 */
@Component
public abstract class AbstractEventMulticaster implements EventMulticaster {

    private List<WeatherListener> listenerList = new ArrayList<>();

    @Override
    public void publish(WeatherEvent event) {
        doStart();
        // 广播事件给所有监听器
        listenerList.forEach(each -> each.onWeatherEvent(event));
        doEnd();
    }

    @Override
    public void addListener(WeatherListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void registerListeners(List<WeatherListener> listeners) {
        listenerList.addAll(listeners);
    }

    @Override
    public void removeListener(WeatherListener listener) {
        listenerList.remove(listener);
    }

    abstract void doStart();

    abstract void doEnd();
}
