package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.RefundDto;

import java.util.List;

public interface RefundService {
    List<RefundDto> getAllByMerchantId();
}
