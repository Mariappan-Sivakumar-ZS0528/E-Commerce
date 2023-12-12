package com.app.shopping.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierAccountInfoDto
{
    private Long id;
    private String accountHolderName;
    private String accountNumber;
    private String sortCode;
    private String bankName;
    private String bankBranch;
}
