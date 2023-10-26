package com.app.shopping.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {
    private Long id;
    private String email;
    private String company;
    private double profitSharingPercentage;
    private String contactNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String postalCode;
    private String supplierName;
    private String designation;
    private String accountHolder;
    private String accountNumber;
    private String sortCode;
    private String bankBranch;

}
