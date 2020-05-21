package io.daff.springboot.service;

import io.daff.springboot.entity.User;

/**
 * @author daffupman
 * @since 2020/5/21
 */
public interface UserService {

    User getUserById(Integer id);
}
