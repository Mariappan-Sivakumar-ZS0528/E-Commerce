package com.app.shopping.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FullwidthDisplayPromos")
public class FullwidthDisplayFeaturedPromos {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String promoTitle;
        @Temporal(TemporalType.DATE)
        private Date startingDate;
        @Temporal(TemporalType.DATE)
        private Date endingDate;
        private String link;
        @Lob
        @Column(length = 1000)
        private byte[] desktopImage;
        private int desktopwidth;
        private int desktopheight;
        private boolean status=true;
        @Lob
        @Column(length = 1000)
        private byte[] mobileImage;
        private int mobilewidth;
        private int mobileheight;
}
