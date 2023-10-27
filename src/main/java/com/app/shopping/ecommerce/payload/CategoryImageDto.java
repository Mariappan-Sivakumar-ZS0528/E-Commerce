package com.app.shopping.ecommerce.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class CategoryImageDto {
    private String mobileImageName;
    private String desktopImageName;
    private String imageType;
    private byte[] mobileImageData;
    private byte[] desktopImageData;
}
