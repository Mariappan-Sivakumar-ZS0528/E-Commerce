package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {
    List<AddressBook> findByCustomerEmail(String email);
}

