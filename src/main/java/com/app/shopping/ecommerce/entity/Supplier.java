package com.app.shopping.ecommerce.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

//import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Supplier is an individual page where certain information of the supplier(who supply the goods)were stored in them.
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String company;
    private double profitSharingPercentage;
    private String contactNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;  // Landmark
    private String city;
    private String postalCode;
    private String supplierName;
    private String designation;
    private String accountHolder;
    private String accountNumber;
    private String sortCode;
    private String bankBranch;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;
}
