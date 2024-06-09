package org.xumin.myshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.Role;
import org.xumin.myshop.reponsitory.RoleReponsitory;
import org.xumin.myshop.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleReponsitory roleReponsitory;

    @Autowired
    public RoleServiceImpl(RoleReponsitory roleReponsitory) {
        this.roleReponsitory = roleReponsitory;
    }

}
