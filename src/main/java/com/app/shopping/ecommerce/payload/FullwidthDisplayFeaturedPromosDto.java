package com.app.shopping.ecommerce.payload;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

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
