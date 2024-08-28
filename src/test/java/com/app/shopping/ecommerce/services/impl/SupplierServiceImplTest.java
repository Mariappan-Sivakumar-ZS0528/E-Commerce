package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.SupplierAccountInfoDto;
import com.app.shopping.ecommerce.payload.SupplierContactDto;
import com.app.shopping.ecommerce.payload.SupplierDto;
import com.app.shopping.ecommerce.payload.SupplierReg;
import com.app.shopping.ecommerce.repository.RoleRepository;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
class SupplierServiceImplTest {
    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private  ModelMapper modelMapper;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private SupplierServiceImpl supplierService;
    Supplier supplier;
    SupplierReg supplierReg;
    SupplierDto supplierDto;
    SupplierContactDto supplierContactDto;
    SupplierAccountInfoDto supplierAccountInfoDto;
    @BeforeEach
    void setUp() {
        supplier=new Supplier();
        supplier.setId(1L);
        supplierReg=new SupplierReg();
        supplierReg.setId(1L);
        supplierDto=new SupplierDto();
        supplierDto.setId(1L);
        supplierContactDto=new SupplierContactDto();
        supplierContactDto.setId(1L);
        supplierAccountInfoDto=new SupplierAccountInfoDto();
        supplierAccountInfoDto.setId(1L);
    }

    @Test
    void registrationSupplier() {
        when(modelMapper.map(supplierReg,Supplier.class)).thenReturn(supplier);
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        when(modelMapper.map(supplier,SupplierReg.class)).thenReturn(supplierReg);
        assertEquals(supplierReg,supplierService.registrationSupplier(supplierReg));
    }

    @Test
    void getSupplierById() {
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(modelMapper.map(supplier, SupplierDto.class)).thenReturn(supplierDto);
        assertEquals(supplierDto,supplierService.getSupplierById(1L));
    }
    @Test
    void getSupplierByIdForFailure(){

        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->supplierService.getSupplierById(1L));
    }

    @Test
    void getAllSuppliers() {
        when(supplierRepository.findAll()).thenReturn(List.of(supplier));
        when(modelMapper.map(supplier,SupplierDto.class)).thenReturn(supplierDto);
        assertEquals(List.of(supplierDto), supplierService.getAllSuppliers());
    }

    @Test
    void updateSupplier() {
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(modelMapper.map(supplierDto,Supplier.class)).thenReturn(supplier);
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        when(modelMapper.map(supplier,SupplierDto.class)).thenReturn(supplierDto);
        assertEquals(supplierDto,supplierService.updateSupplier(1L,supplierDto));
    }
    @Test
    void updateSupplierForFailure(){

        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->supplierService.updateSupplier(1L,supplierDto));
    }

    @Test
    void deleteSupplier() {
        when(supplierRepository.existsById(1L)).thenReturn(true);
        assertEquals(true,supplierService.deleteSupplier(1L));
    }
    @Test
    void deleteSupplierForNullData(){
        when(supplierRepository.existsById(1L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class,()->supplierService.deleteSupplier(1L));
    }

    @Test
    void addSupplierContact() {
        when(modelMapper.map(supplierContactDto,Supplier.class)).thenReturn(supplier);
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        when(modelMapper.map(supplier,SupplierContactDto.class)).thenReturn(supplierContactDto);
        assertEquals(supplierContactDto,supplierService.addSupplierContact(supplierContactDto));
    }

    @Test
    void getSupplierContactById() {
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(modelMapper.map(supplier,SupplierContactDto.class)).thenReturn(supplierContactDto);
        assertEquals(supplierContactDto,supplierService.getSupplierContactById(1L));
    }
    @Test
    void getSupplierContactByIdForNullData(){
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->supplierService.getSupplierContactById(1L));
    }

    @Test
    void updateSupplierContact() {
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(modelMapper.map(supplierContactDto,Supplier.class)).thenReturn(supplier);
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        when(modelMapper.map(supplier,SupplierContactDto.class)).thenReturn(supplierContactDto);
        assertEquals(supplierContactDto,supplierService.UpdateSupplierContact(1L,supplierContactDto));
    }
    @Test
    void updateSupplierContactForNullData(){
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->supplierService.UpdateSupplierContact(1L,supplierContactDto));
    }

    @Test
    void addSupplierAccountInfo() {
        when(modelMapper.map(supplierAccountInfoDto,Supplier.class)).thenReturn(supplier);
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        when(modelMapper.map(supplier,SupplierAccountInfoDto.class)).thenReturn(supplierAccountInfoDto);
        assertEquals(supplierAccountInfoDto,supplierService.addSupplierAccountInfo(supplierAccountInfoDto));
    }

    @Test
    void updateSupplierAccountInfo() {
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(modelMapper.map(supplierAccountInfoDto,Supplier.class)).thenReturn(supplier);
        when(supplierRepository.save(supplier)).thenReturn(supplier);
        when(modelMapper.map(supplier,SupplierAccountInfoDto.class)).thenReturn(supplierAccountInfoDto);
        assertEquals(supplierAccountInfoDto,supplierService.UpdateSupplierAccountInfo(1L,supplierAccountInfoDto));
    }
    @Test
    void updateSupplierAccountInfoForNullData(){
        when(supplierRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->supplierService.UpdateSupplierAccountInfo(1L,supplierAccountInfoDto));
    }

    @Test
    void getSupplierAccountInfoById() {
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(modelMapper.map(supplier,SupplierAccountInfoDto.class)).thenReturn(supplierAccountInfoDto);
        assertEquals(supplierAccountInfoDto,supplierService.getSupplierAccountInfoById(1L));    }
}