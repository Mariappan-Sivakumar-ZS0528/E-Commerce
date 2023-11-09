package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.SupplierDto;
import com.app.shopping.ecommerce.payload.SupplierReg;

import java.util.List;

public interface SupplierService {
    SupplierReg registrationSupplier(SupplierReg supplierreg);

    SupplierDto getSupplierById(Long id);

    List<SupplierDto> getAllSuppliers();

    SupplierDto updateSupplier(Long id, SupplierDto updatedSupplier);

    boolean deleteSupplier(Long id);
}
