package io.daff.springboot.service.impl;

import io.daff.springboot.entity.User;
import io.daff.springboot.mapper.UserMapper;
import io.daff.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author daffupman
 * @since 2020/5/21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return Optional
                .ofNullable(userMapper.selectByPrimaryKey(id))
                .orElse(null);
    }
}
