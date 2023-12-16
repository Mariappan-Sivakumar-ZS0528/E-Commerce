package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.Product;
import com.app.shopping.ecommerce.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT p FROM Supplier p WHERE " +
            "p.company LIKE CONCAT('%',:query,'%')") //JPQL
    List<Supplier> search(String query);
}
