package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.AddressBookDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface AddressBookService {
    AddressBookDto createAddressBook(AddressBookDto addressBookDto, HttpServletRequest request);
    AddressBookDto getAddressBookById(Long id, HttpServletRequest request);
    List<AddressBookDto> getAllAddressBook(HttpServletRequest request);
    AddressBookDto updateAddressBook(Long id,AddressBookDto addressBookDto, HttpServletRequest request);
    String deleteAddressBook(Long id, HttpServletRequest request);
    String setDefault(Long id, HttpServletRequest request);
}
