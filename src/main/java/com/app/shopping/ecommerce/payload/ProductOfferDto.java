package com.app.shopping.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfferDto {
    private Long id;
    private boolean isActive;
    private String offerDurationType;
    private Date startingDate;
    private Date endingDate;
    private int discount;
}
