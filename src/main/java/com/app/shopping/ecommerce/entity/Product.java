package com.app.shopping.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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
    private double price;
    private int inventory;
    private int quantity;
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
    @Column(length = 1000000)
    private byte[] imageData3;
    @Lob
    @Column(length = 1000000)
    private byte[] imageData4;
    private String imageType1;
    private String imageType2;
    private String imageType3;
    private String imageType4;
    private boolean isActive;
    private String offerDurationType;
    @Temporal(TemporalType.DATE)
    private Date startingDate;
    @Temporal(TemporalType.DATE)
    private Date endingDate;
    private double discount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    public boolean isActive() {
        Date currentDate = new Date();
        if (currentDate.after(this.getStartingDate()) && currentDate.before(this.getEndingDate())) {
            this.setActive(true); // Set isActive to true if the current date is in between start and end dates.
        } else {
            this.setActive(false); // Set isActive to false if the current date is outside the range.
        }
        return this.isActive;
    }
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;
}