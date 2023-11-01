package com.app.shopping.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int unit;
    private int discount;
    private String imageName1;
    private String imageName2;
    private String imageName3;
    private String imageName4;
    @Lob
    @Column(length = 100000)
    private byte[] imageData1;
    @Lob
    @Column(length = 1000000)
    private byte[] imageData2;
    @Lob
    @Column(length = 10000)
    private byte[] imageData3;
    @Lob
    @Column(length = 10000)
    private byte[] imageData4;
    private String imageType1;
    private String imageType2;
    private String imageType3;
    private String imageType4;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
