package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.AddressBook;
import com.app.shopping.ecommerce.entity.Customer;
import com.app.shopping.ecommerce.payload.AddressBookDto;
import com.app.shopping.ecommerce.repository.AddressBookRepository;
import com.app.shopping.ecommerce.repository.CustomerRepository;
import com.app.shopping.ecommerce.services.AddressBookService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AddressBookServiceImplTest {
    @Autowired
    private AddressBookService addressBookService;
    @MockBean
    private AddressBookRepository addressBookRepository;
    @MockBean
    private CustomerRepository customerRepository;
//    @MockBean
//    private ModelMapper modelMapper;

    MockHttpServletRequest request = new MockHttpServletRequest();
    @BeforeEach
    public void setUp() {
        AddressBook addressBook = new AddressBook();
        addressBook.setId(1L);
        addressBook.setAddressLine1("123 Main St");
        addressBook.setAddressLine2("");
        addressBook.setCity("Tuticorin");
        addressBook.setPostalCode("628001");
        addressBook.setNickName("My Home");
        addressBook.setLabel("Home");
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setEmail("spotmari@gmail.com");
        customer.setName("Mariappan Sivakumar");
        customer.setAddressBooks(Set.of(addressBook));
        addressBook.setCustomer(customer);
        Optional<AddressBook> optionalAddressBook = Optional.of(addressBook);
        List<AddressBook> listAddressBook = List.of(addressBook);
        Mockito.when(addressBookRepository.findByCustomerEmail("spotmari@gmail.com")).thenReturn(listAddressBook);
        Mockito.when(addressBookRepository.findById(1L)).thenReturn(optionalAddressBook);
        Mockito.when(addressBookRepository.save(Mockito.any(AddressBook.class))).thenReturn(addressBook);
        Mockito.when(customerRepository.findByEmail("mari@gmail.com")).thenReturn(Optional.of(customer));
        request.addHeader("Authorization", "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJtYXJpQGdtYWlsLmNvbSIsImlhdCI6MTcyMTA0NDIyMiwiZXhwIjoxNzIxNjQ5MDIyfQ.9ypNowsrebM_Q7felB8A8CM2dwCi1icJig-Vyki30FC86x1pbzfHxRMXv6L3UiuJ");
    }
    @Test
    public void testFindById() {
        AddressBookDto addressBook = addressBookService.getAddressBookById(1L, request);
        assertEquals("123 Main St", addressBook.getAddressLine1());
        assertEquals("Mariappan Sivakumar", addressBook.getCustomerName());
    }
    @Test
    public void testGetAllAddressBook() {
        List<AddressBookDto> addressBook = addressBookService.getAllAddressBook(request);
        assertEquals(0, addressBook.size());
//        assertEquals("123 Main St", addressBook.get(0).getAddressLine1());
    }
    @Test
    public void testCreateAddressBook() {
        AddressBookDto addressBookDto=new AddressBookDto();
        addressBookDto.setId(1L);
        addressBookDto.setAddressLine1("123 Main St");
        addressBookDto.setAddressLine2("");
        addressBookDto.setCity("Tuticorin");
        addressBookDto.setPostalCode("628001");
        addressBookDto.setNickName("My Home");
        addressBookDto.setLabel("Home");
//        Mockito.when(modelMapper.map(addressBookDto, AddressBookDto.class)).thenReturn(addressBookDto);
        AddressBookDto addressBook = addressBookService.createAddressBook(addressBookDto, request);
        assertEquals("123 Main St", addressBook.getAddressLine1());
    }
    @Test
    public void testUpdateAddressBook() {
        AddressBookDto addressBookDto=new AddressBookDto();
        addressBookDto.setId(1L);
        addressBookDto.setAddressLine1("123 Levinjirpuram St");
        addressBookDto.setAddressLine2("");
        addressBookDto.setCity("Tuticorin");
        addressBookDto.setPostalCode("628001");
        addressBookDto.setNickName("My Home");
        addressBookDto.setLabel("Home");
        AddressBookDto addressBook = addressBookService.updateAddressBook(1L, addressBookDto, request);
        assertEquals("123 Levinjirpuram St", addressBook.getAddressLine1());
    }
    @Test
    public void testDeleteAddressBook() {
       assertEquals("Address Deleted Successfully", addressBookService.deleteAddressBook(1L, request));
    }
    @Test
    public void testSetDefault() {
        assertEquals("Address Set as Default", addressBookService.setDefault(1L, request));
    }
}