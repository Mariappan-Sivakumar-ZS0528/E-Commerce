package com.app.shopping.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "refunds")
public class Refund
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderByName;
    private Long itemCount;
    private String productNameDetails;
    private Date placedOn;
    private String refundAmount;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;
}
