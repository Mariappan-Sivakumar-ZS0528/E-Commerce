package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.SupplierAccountInfoDto;
import com.app.shopping.ecommerce.payload.SupplierContactDto;
import com.app.shopping.ecommerce.payload.SupplierDto;
import com.app.shopping.ecommerce.payload.SupplierReg;

import java.util.List;

public interface SupplierService {
    SupplierReg RegistrationSupplier(SupplierReg supplierReg);

    SupplierDto getSupplierById(Long id);

    List<SupplierDto> getAllSuppliers();

    SupplierDto updateSupplier(Long id, SupplierDto updatedSupplier);

    boolean deleteSupplier(Long id);
    SupplierContactDto addSupplierContact(SupplierContactDto newSupplierContact);
    SupplierContactDto getSupplierContactById(Long id);
    SupplierContactDto UpdateSupplierContact(Long id, SupplierContactDto updatedSupplierContact);

    SupplierAccountInfoDto getSupplierAccountInfoById(Long id);

    SupplierAccountInfoDto addSupplierAccountInfo(SupplierAccountInfoDto newSupplierAccountInfoDto);

    SupplierAccountInfoDto UpdateSupplierAccountInfo(Long id, SupplierAccountInfoDto updatedSupplierAccountInfoDto);
}
