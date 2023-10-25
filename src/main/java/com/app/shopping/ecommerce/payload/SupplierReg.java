package com.app.shopping.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierReg {
    private Long id;
    private String email;
    private String companyname;
    private double profitSharingPercentage;
    private String contactNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String postalCode;

}
