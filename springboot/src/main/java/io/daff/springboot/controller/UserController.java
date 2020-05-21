package io.daff.springboot.controller;

import io.daff.springboot.entity.User;
import io.daff.springboot.service.TestService;
import io.daff.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author daffupman
 * @since 2020/5/21
 */
@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TestService testService;

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Integer id) {
        return Optional.ofNullable(userService.getUserById(id)).map(User::toString).orElse("NoSuchUser");
    }

    @GetMapping("/test1")
    public String test1() {
        return testService.test1();
    }

    @GetMapping("/test2")
    public String test2() {
        return testService.test2();
    }

    @GetMapping("/test3")
    public String test3() {
        return testService.test3();
    }

}
