package io.daff.springbootguide.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * for循环实现计算服务
 *
 * @author daffupman
 * @since 2020/5/30
 */
@Profile("Java7") // 必须配置profile才能使用这个Bean
@Service
public class Java7CalculateService implements CalculateService {

    @Override
    public Integer sum(Integer... values) {
        System.out.println("use Java8");
        int sum = 0;
        for (Integer value : values) {
            sum += value;
        }
        return sum;
    }
}
