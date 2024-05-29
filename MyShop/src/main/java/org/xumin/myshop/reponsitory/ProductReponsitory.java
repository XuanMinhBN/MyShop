package org.xumin.myshop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xumin.myshop.entity.Product;

import java.util.List;

@Repository
public interface ProductReponsitory extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long id);

    List<Product> findByNameContaining(String name);
}
