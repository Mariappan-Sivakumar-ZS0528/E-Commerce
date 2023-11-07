package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.AddressBookDto;
import com.app.shopping.ecommerce.services.AddressBookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/address-book")
public class AddressBookController {
    private AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<AddressBookDto> addAddressBook(@RequestBody AddressBookDto addressBookDto, HttpServletRequest request){
        return new ResponseEntity<>(addressBookService.createAddressBook(addressBookDto, request), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<AddressBookDto> getAddressBook(@PathVariable Long id, HttpServletRequest request){
        return new ResponseEntity<>(addressBookService.getAddressBookById(id, request), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<AddressBookDto>> getAllAddressBook(HttpServletRequest request){
        return new ResponseEntity<>(addressBookService.getAllAddressBook(request), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<AddressBookDto> updateAddressBook(@PathVariable Long id, @RequestBody AddressBookDto addressBookDto, HttpServletRequest request){
        return new ResponseEntity<>(addressBookService.updateAddressBook(id, addressBookDto, request), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddressBook(@PathVariable Long id, HttpServletRequest request){
        return new ResponseEntity<>(addressBookService.deleteAddressBook(id, request), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/set-default/{id}")
    public ResponseEntity<String> setDefault(@PathVariable Long id, HttpServletRequest request){
        return new ResponseEntity<>(addressBookService.setDefault(id, request), HttpStatus.OK);
    }

}
