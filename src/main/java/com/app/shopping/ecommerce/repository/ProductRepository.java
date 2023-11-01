package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.Product;
import com.app.shopping.ecommerce.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySupplier(Supplier supplier);
    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%',:query,'%')" +
            "Or p.description LIKE CONCAT('%',:query,'%')") //JPQL
    List<Product> search(String query);
}
