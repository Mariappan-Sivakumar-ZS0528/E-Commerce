package com.app.shopping.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "imageData2")
public class ImageData2
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String mobileImageName;
    private String desktopImageName;
    private String imageType;
    @Lob
    @Column(name = "mobileImageData",length = 1000)
    private byte[] mobileImageData;
    @Lob
    @Column(name = "desktopImageData",length = 1000)
    private byte[] desktopImageData;
}
