package io.daff.springbootguide.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * for循环实现计算服务
 *
 * @author daffupman
 * @since 2020/5/30
 */
@Profile("Java8")
@Service
public class Java8CalculateService implements CalculateService {

    @Override
    public Integer sum(Integer... values) {
        System.out.println("use Java 8");
        return Stream.of(values).reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        System.out.println(new Java7CalculateService().sum(1, 2, 3, 4, 5));
        System.out.println(new Java8CalculateService().sum(1, 2, 3, 4, 5));
    }
}
