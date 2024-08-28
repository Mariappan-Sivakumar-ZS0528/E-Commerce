package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.AddressBookDto;
import com.app.shopping.ecommerce.services.AddressBookService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AddressBookControllerTest {
    @Autowired
    AddressBookController addressBookController;

    @MockBean
    AddressBookService addressBookService;

    AddressBookDto addressBookDto;
    List<AddressBookDto> addressBookDtoList;
    HttpServletRequest request;

    @BeforeEach
    void setUp() {
        addressBookDto = new AddressBookDto();
        addressBookDtoList = List.of(addressBookDto);
        request= Mockito.mock(HttpServletRequest.class);
        Mockito.when(addressBookService.createAddressBook(addressBookDto, request)).thenReturn(addressBookDto);
        Mockito.when(addressBookService.getAddressBookById(1L, request)).thenReturn(addressBookDto);
        Mockito.when(addressBookService.getAllAddressBook(request)).thenReturn(addressBookDtoList);
        Mockito.when(addressBookService.updateAddressBook(1L, addressBookDto, request)).thenReturn(addressBookDto);
        Mockito.when(addressBookService.deleteAddressBook(1L, request)).thenReturn("AddressBook deleted successfully");
        Mockito.when(addressBookService.setDefault(1L, request)).thenReturn("AddressBook set as default successfully");
    }

    @Test
    @WithMockUser(roles = "USER")
    void addAddressBook() {
        ResponseEntity<AddressBookDto> responseEntity = addressBookController.addAddressBook(addressBookDto,request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(addressBookDto, responseEntity.getBody());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAddressBook() {
        ResponseEntity<AddressBookDto> responseEntity = addressBookController.getAddressBook(1L, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(addressBookDto, responseEntity.getBody());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAllAddressBook() {
        ResponseEntity<List<AddressBookDto>> responseEntity = addressBookController.getAllAddressBook(request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(addressBookDtoList, responseEntity.getBody());
    }

    @Test
    @WithMockUser(roles = "USER")
    void updateAddressBook() {
        ResponseEntity<AddressBookDto> responseEntity = addressBookController.updateAddressBook(1L, addressBookDto, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(addressBookDto, responseEntity.getBody());
    }

    @Test
    @WithMockUser(roles = "USER")
    void deleteAddressBook() {
        ResponseEntity<String> responseEntity = addressBookController.deleteAddressBook(1L, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("AddressBook deleted successfully", responseEntity.getBody());
    }

    @Test
    @WithMockUser(roles = "USER")
    void setDefault() {
        ResponseEntity<String> responseEntity = addressBookController.setDefault(1L, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("AddressBook set as default successfully", responseEntity.getBody());
    }
}