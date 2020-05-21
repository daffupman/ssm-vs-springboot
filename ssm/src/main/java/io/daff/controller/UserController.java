package io.daff.controller;

import io.daff.entity.User;
import io.daff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    @ResponseBody
    public String hello(@PathVariable("id") Integer id) {
        return Optional
                .ofNullable(userService.getUserById(id))
                .map(User::toString).orElse("empty User");
    }
}
