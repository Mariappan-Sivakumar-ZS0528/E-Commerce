package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.RefundDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface RefundService {
    List<RefundDto> getBySupplier(HttpServletRequest request);
    String statusUpdate(Long id, RefundDto refundDto);
}
