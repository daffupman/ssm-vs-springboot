package io.daff.springboot.event;

import io.daff.springboot.event.event.RainEvent;
import io.daff.springboot.event.event.SnowEvent;
import io.daff.springboot.event.listener.RainListener;
import io.daff.springboot.event.listener.SnowListener;
import io.daff.springboot.event.multicaster.WeatherEventMulticaster;

import java.util.Arrays;

/**
 * 监听器的四要素：
 *  - 事件
 *  - 监听器
 *  - 广播器
 *  - 触发机制
 *
 * @author daffupman
 * @since 2020/5/22
 */
public class Test {

    public static void main(String[] args) {
        WeatherEventMulticaster multicaster = new WeatherEventMulticaster();
        // 注册监听器
        RainListener rainListener = new RainListener();
        SnowListener snowListener = new SnowListener();
        multicaster.registerListeners(Arrays.asList(
                rainListener,
                snowListener
        ));
        multicaster.publish(new SnowEvent());
        // multicaster.publish(new RainEvent());

        multicaster.removeListener(snowListener);
        multicaster.publish(new SnowEvent());
    }
}
