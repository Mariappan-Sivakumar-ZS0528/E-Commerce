package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    OrderProduct findByOrderIdAndProductId(Long orderId, Long productId);
}
