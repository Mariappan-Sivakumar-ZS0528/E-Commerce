package com.app.shopping.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierContactDto {
    private Long id;
    private String contactPerson;
    private String designation;
    private String contactNumber;
    private String email;
}
