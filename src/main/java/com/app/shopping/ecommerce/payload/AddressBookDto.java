package com.app.shopping.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDto {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String postalCode;
    private String city;
    private String label;
    private String nickName;
    private boolean isDefault;
    private String customerName;
}
