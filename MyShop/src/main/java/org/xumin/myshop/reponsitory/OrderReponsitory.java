package org.xumin.myshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xumin.myshop.entity.Order;

@Repository
public interface OrderReponsitory extends JpaRepository<Order, Integer> {
}
