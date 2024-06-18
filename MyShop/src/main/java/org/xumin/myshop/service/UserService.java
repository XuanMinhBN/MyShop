package org.xumin.myshop.service;

import org.xumin.myshop.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    List<User> findAll();
}
