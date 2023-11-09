package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Role;
import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.entity.User;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.SupplierDto;
import com.app.shopping.ecommerce.payload.SupplierPassword;
import com.app.shopping.ecommerce.payload.SupplierReg;
import com.app.shopping.ecommerce.repository.RoleRepository;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.repository.UserRepository;
import com.app.shopping.ecommerce.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SupplierServiceImpl implements SupplierService {

    Logger logger= LoggerFactory.getLogger(SupplierServiceImpl.class);
    private final SupplierRepository supplierRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleRepository roleRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.supplierRepository = supplierRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public SupplierReg registrationSupplier(SupplierReg supplierreg) {
        Supplier supplier = modelMapper.map(supplierreg, Supplier.class);
        return modelMapper.map(supplierRepository.save(supplier), SupplierReg.class);
    }

    @Override
    public SupplierDto getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id));
        return modelMapper.map(supplier, SupplierDto.class);
    }
    @Override
    public List<SupplierDto> getAllSuppliers() {

        return supplierRepository.findAll().stream().map(supplier -> modelMapper.map(supplier, SupplierDto.class)).toList();
    }

    @Override
    public SupplierDto updateSupplier(Long id, SupplierDto updatedSupplier) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(id);
        if (existingSupplier.isPresent()) {
            updatedSupplier.setId(id);
            Supplier supplier = modelMapper.map(updatedSupplier, Supplier.class);
            return modelMapper.map(supplierRepository.save(supplier), SupplierDto.class);
        } else {
            throw new ResourceNotFoundException("Supplier", "id", id);
        }
    }

    @Override
    public boolean deleteSupplier(Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Supplier", "id", id);
        }
    }

}
