package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookDtoTest {
    AddressBookDto addressBookDto;

    @BeforeEach
    void setUp() {
        addressBookDto=new AddressBookDto(1L, "addressLine1", "addressLine2", "addressLine3", "postalCode", "city", "label", "nickName", true, "customerName");
    }

    @Test
    void getId() {
        assertEquals(1L, addressBookDto.getId());
    }

    @Test
    void getAddressLine1() {
        assertEquals("addressLine1", addressBookDto.getAddressLine1());
    }

    @Test
    void getAddressLine2() {
        assertEquals("addressLine2", addressBookDto.getAddressLine2());
    }

    @Test
    void getAddressLine3() {
        assertEquals("addressLine3", addressBookDto.getAddressLine3());
    }

    @Test
    void getPostalCode() {
        assertEquals("postalCode", addressBookDto.getPostalCode());
    }

    @Test
    void getCity() {
        assertEquals("city", addressBookDto.getCity());
    }

    @Test
    void getLabel() {
        assertEquals("label", addressBookDto.getLabel());
    }

    @Test
    void getNickName() {
        assertEquals("nickName", addressBookDto.getNickName());
    }

    @Test
    void isDefault() {
        assertTrue(addressBookDto.isDefault());
    }

    @Test
    void getCustomerName() {
        assertEquals("customerName", addressBookDto.getCustomerName());
    }

    @Test
    void setId() {
        addressBookDto.setId(2L);
        assertEquals(2L, addressBookDto.getId());
    }

    @Test
    void setAddressLine1() {
        addressBookDto.setAddressLine1("addressLine 1");
        assertEquals("addressLine 1", addressBookDto.getAddressLine1());
    }

    @Test
    void setAddressLine2() {
        addressBookDto.setAddressLine2("addressLine 2");
        assertEquals("addressLine 2", addressBookDto.getAddressLine2());
    }

    @Test
    void setAddressLine3() {
        addressBookDto.setAddressLine3("addressLine 3");
        assertEquals("addressLine 3", addressBookDto.getAddressLine3());
    }

    @Test
    void setPostalCode() {
        addressBookDto.setPostalCode("postal Code");
        assertEquals("postal Code", addressBookDto.getPostalCode());
    }

    @Test
    void setCity() {
        addressBookDto.setCity("city1");
        assertEquals("city1", addressBookDto.getCity());
    }

    @Test
    void setLabel() {
        addressBookDto.setLabel("label1");
        assertEquals("label1", addressBookDto.getLabel());
    }

    @Test
    void setNickName() {
        addressBookDto.setNickName("nickName1");
        assertEquals("nickName1", addressBookDto.getNickName());
    }

    @Test
    void setDefault() {
        addressBookDto.setDefault(false);
        assertFalse(addressBookDto.isDefault());
    }

    @Test
    void setCustomerName() {
        addressBookDto.setCustomerName("customerName1");
        assertEquals("customerName1", addressBookDto.getCustomerName());
    }
}