package com.app.shopping.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    private String mobileImageName;
    private String desktopImageName;
    private String thumbnailImageName;
    private String mobileImageType;
    private String desktopImageType;
    private String thumbnailImageType;
    @Lob
    @Column(length = 100000)
    private byte[] mobileImageData;
    @Lob
    @Column(length = 1000000)
    private byte[] desktopImageData;
    @Lob
    @Column(length = 10000)
    private byte[] thumbnailImageData;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubCategory> subCategories;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}
