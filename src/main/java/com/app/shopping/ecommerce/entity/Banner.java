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
@Table(name = "banners")
public class Banner
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String mobileImageName;
    private String desktopImageName;
    private String imageType;
    private String mobileLink;
    private String desktopLink;
    private String title;
    @Lob
    @Column(name = "mobileImageData",length = 1000)
    private byte[] mobileImageData;
    @Lob
    @Column(name = "desktopImageData",length = 1000)
    private byte[] desktopImageData;
}
