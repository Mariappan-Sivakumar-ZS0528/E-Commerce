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
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int inventory;
    private int quantity;
    private int discount;
    private String imageName1;
    private String imageName2;
    private String imageName3;
    private String imageName4;
    private String subCategoryName;
    private String categoryName;
    private boolean isActive;
    private String offerDurationType;
    private Date startingDate;
    private Date endingDate;
}
