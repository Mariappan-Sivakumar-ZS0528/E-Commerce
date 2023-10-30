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
public class DisplayInTilesPromosDto {
    private Long id;
    private String displayPromoTitle;
    private Date startingDate;
    private Date endingDate;
    private String link;
}
