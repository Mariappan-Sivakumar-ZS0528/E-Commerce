package com.app.shopping.ecommerce.payload;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullwidthDisplayFeaturedPromosDto {
    private Long id;
    private String promoTitle;
    private Date startingDate;
    private Date endingDate;
    private String link;
}
