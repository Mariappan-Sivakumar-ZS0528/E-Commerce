package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.Legal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalRepository extends JpaRepository<Legal, Long> {

}
