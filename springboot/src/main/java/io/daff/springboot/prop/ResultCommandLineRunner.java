package io.daff.springboot.prop;

import org.springframework.beans.factory.Aware;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/24
 */
@Component
public class ResultCommandLineRunner implements CommandLineRunner, EnvironmentAware, MyAware {

    private Environment env;
    private Flag flag;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("name1 = " + env.getProperty("name1"));
        System.out.println("name2 = " + env.getProperty("name2"));
        // 随机值属性
        System.out.println("age = " + env.getProperty("age"));
        // 操作系统的环境变量
        System.out.println("system.path = " + env.getProperty("system.path"));
        // 获取jvm属性
        System.out.println("vm.name = " + env.getProperty("vm.name"));

        System.out.println("canOperate = " + flag.isCanOperate());
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Override
    public void setFlag(Flag flag) {
        this.flag = flag;
    }
}
