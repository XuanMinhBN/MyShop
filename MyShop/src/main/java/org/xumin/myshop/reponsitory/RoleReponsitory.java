package org.xumin.myshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.xumin.myshop.entity.Role;

import java.util.List;

@Repository
public interface RoleReponsitory extends JpaRepository<Role, Long> {
}
