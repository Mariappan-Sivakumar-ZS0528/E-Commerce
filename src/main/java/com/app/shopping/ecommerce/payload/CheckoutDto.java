package com.app.shopping.ecommerce.payload;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDto {
    private Long addressBookId;
    private Date deliveryOn;
}
