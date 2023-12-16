package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByCustomerId(Long customerId);
    void deleteAllByCustomerId(Long customerId);
    @Transactional
    void deleteByProductIdAndCustomerId(Long productId, Long customerId);
    Cart findByProductIdAndCustomerId(Long productId, Long customerId);
}
