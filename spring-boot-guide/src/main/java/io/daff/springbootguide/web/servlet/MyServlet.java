package io.daff.springbootguide.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet3.0 支持注解方式，并且需要继承HttpServlet。
 * 在SpringBoot启动类上添加@ServletComponentScan，同时指定扫描的包名。
 * filter、listener同理。
 *
 * @author daffupman
 * @since 2020/5/28
 */
@WebServlet(urlPatterns = "/my/servlet", asyncSupported = true)    // 配置访问路径
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 开启异步
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            // 异步需要执行的逻辑
            try {
                resp.getWriter().println("Hello World!");
                // 需求显示地触发完成
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
