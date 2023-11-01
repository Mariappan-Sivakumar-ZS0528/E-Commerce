package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.Refund;
import com.app.shopping.ecommerce.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefundRepository  extends JpaRepository<Refund, Long>
{
    List<Refund> findBySupplier(Supplier supplier);
}
