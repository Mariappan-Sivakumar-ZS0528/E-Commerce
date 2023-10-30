package com.app.shopping.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageMeasurementsDto {
    private int mobilewidth;
    private int mobileheight;
    private int desktopwidth;
    private int desktopheight;
}
