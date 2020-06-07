package io.daff.springbootguide.web.template.engine;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Thymeleaf模板引擎启动类
 *
 * @author daffupman
 * @since 2020/6/7
 */
public class ThymeleafTemplateEngineBootstrap {

    public static void main(String[] args) throws IOException {
        // 构建引擎
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // 创建渲染上下文
        Context context = new Context();
        context.setVariable("message", "hello world");

        // 读取模板内容
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/templates/thymeleaf/hello.html");
        // 文件输入流
        FileInputStream fileInputStream = new FileInputStream(resource.getFile());
        // 字节输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.copy(fileInputStream, byteArrayOutputStream);
        fileInputStream.close();

        // 模板的内容
        // String content = "<p th:text=\"${message}\">!!!</p>";
        String content = byteArrayOutputStream.toString("UTF-8");

        // 渲染结果
        String result = templateEngine.process(content, context);
        System.out.println(result);
    }
}
