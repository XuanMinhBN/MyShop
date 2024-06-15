package org.xumin.myshop.service;

import org.xumin.myshop.entity.User;

public interface UserService {
    User findByUsername(String username);
}
