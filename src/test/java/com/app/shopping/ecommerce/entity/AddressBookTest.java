package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {
    AddressBook addressBook;
    Customer customer;

    @BeforeEach
    void setUp() {
        customer=new Customer();
        addressBook = new AddressBook(1L, "addressLine1", "addressLine2", "addressLine3", "postalCode", "city", "label", "nickName", true,customer);
    }

    @Test
    void testToString() {
        assertEquals("addressLine1 addressLine2 addressLine3 postalCode city", addressBook.toString());
    }

    @Test
    void getId() {
        assertEquals(1L, addressBook.getId());
    }

    @Test
    void getAddressLine1() {
        assertEquals("addressLine1", addressBook.getAddressLine1());
    }

    @Test
    void getAddressLine2() {
        assertEquals("addressLine2", addressBook.getAddressLine2());
    }

    @Test
    void getAddressLine3() {
        assertEquals("addressLine3", addressBook.getAddressLine3());
    }

    @Test
    void getPostalCode() {
        assertEquals("postalCode", addressBook.getPostalCode());
    }

    @Test
    void getCity() {
        assertEquals("city", addressBook.getCity());
    }

    @Test
    void getLabel() {
        assertEquals("label", addressBook.getLabel());
    }

    @Test
    void getNickName() {
        assertEquals("nickName", addressBook.getNickName());
    }

    @Test
    void isDefault() {
        assertTrue(addressBook.isDefault());
    }

    @Test
    void getCustomer() {
        assertEquals(customer, addressBook.getCustomer());
    }

    @Test
    void setId() {
        addressBook.setId(2L);
        assertEquals(2L, addressBook.getId());
    }

    @Test
    void setAddressLine1() {
        addressBook.setAddressLine1("address Line1");
        assertEquals("address Line1", addressBook.getAddressLine1());
    }

    @Test
    void setAddressLine2() {
        addressBook.setAddressLine2("address Line2");
        assertEquals("address Line2", addressBook.getAddressLine2());
    }

    @Test
    void setAddressLine3() {
        addressBook.setAddressLine3("address Line3");
        assertEquals("address Line3", addressBook.getAddressLine3());
    }

    @Test
    void setPostalCode() {
        addressBook.setPostalCode("postal Code");
        assertEquals("postal Code", addressBook.getPostalCode());
    }

    @Test
    void setCity() {
        addressBook.setCity("city name");
        assertEquals("city name", addressBook.getCity());
    }

    @Test
    void setLabel() {
        addressBook.setLabel("label name");
        assertEquals("label name", addressBook.getLabel());
    }

    @Test
    void setNickName() {
        addressBook.setNickName("nick name");
        assertEquals("nick name", addressBook.getNickName());
    }

    @Test
    void setDefault() {
        addressBook.setDefault(false);
        assertFalse(addressBook.isDefault());
    }

    @Test
    void setCustomer() {
        Customer customer=new Customer();
        addressBook.setCustomer(customer);
        assertEquals(customer, addressBook.getCustomer());
    }
}