package org.xumin.myshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xumin.myshop.entity.User;

@Repository
public interface UserReponsitory extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
