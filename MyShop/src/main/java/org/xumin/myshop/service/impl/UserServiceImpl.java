package org.xumin.myshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.AuthUser;
import org.xumin.myshop.entity.Role;
import org.xumin.myshop.entity.UserAddress;
import org.xumin.myshop.entity.User;
import org.xumin.myshop.reponsitory.RoleReponsitory;
import org.xumin.myshop.reponsitory.UserReponsitory;
import org.xumin.myshop.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserReponsitory userReponsitory;
    private final RoleReponsitory roleReponsitory;

    @Autowired
    public UserServiceImpl(UserReponsitory userReponsitory, RoleReponsitory roleReponsitory) {
        this.userReponsitory = userReponsitory;
        this.roleReponsitory = roleReponsitory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userReponsitory.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        Set<Role> roles = user.getRoles();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (roles != null) {
            for (Role role : roles) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
                authorities.add(grantedAuthority);
            }
        }

        return new AuthUser(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getId(),
                user.getCreatedAt(),
                user.isDeleteStatus(),
                user.isStatus()
        );
    }


    @Override
    public User findByUsername(String username) {
        return userReponsitory.findByUsername(username);
    }
}
