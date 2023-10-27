package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.payload.SupplierDto;
import com.app.shopping.ecommerce.payload.SupplierReg;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    SupplierReg RegistrationSupplier(SupplierReg supplierreg);

    SupplierDto getSupplierById(Long id);

    List<SupplierDto> getAllSuppliers();

    SupplierDto updateSupplier(Long id, SupplierDto updatedSupplier);

    boolean deleteSupplier(Long id);
}
