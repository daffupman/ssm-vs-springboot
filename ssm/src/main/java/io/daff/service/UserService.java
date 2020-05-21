package io.daff.service;

import io.daff.entity.User;
import io.daff.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author daffupman
 * @since 2020/5/21
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(Integer id) {
        return Optional
                .ofNullable(userMapper.selectByPrimaryKey(id))
                .orElse(null);
    }
}
