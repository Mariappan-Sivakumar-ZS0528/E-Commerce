package com.app.shopping.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefundDto
{
    private Long orderId;
    private String orderByName;
    private Long itemCount;
    private String productNameDetails;
    private Date placedOn;
    private String refundAmount;
    private int status;
}
