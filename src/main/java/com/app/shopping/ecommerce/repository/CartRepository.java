package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.Cart;
import com.app.shopping.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomer(Customer customer);
    List<Cart> findAllByCustomerId(Long customerId);
    void deleteAllByCustomerId(Long customerId);
}
