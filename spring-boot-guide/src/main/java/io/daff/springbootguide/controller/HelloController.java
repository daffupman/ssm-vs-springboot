package io.daff.springbootguide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author daffupman
 * @since 2020/6/7
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ModelAttribute("message")
    public String message() {
        return "哈哈哈。。。";
    }
}
