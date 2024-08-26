package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutDtoTest {
    CheckoutDto checkoutDto;

    @BeforeEach
    void setUp() {
        checkoutDto = new CheckoutDto();
        checkoutDto = new CheckoutDto(1L, new Date());
    }

    @Test
    void getAddressBookId() {
        assertEquals(1L, checkoutDto.getAddressBookId());
    }

    @Test
    void getDeliveryOn() {
        assertEquals(new Date(), checkoutDto.getDeliveryOn());
    }

    @Test
    void setAddressBookId() {
        checkoutDto.setAddressBookId(2L);
        assertEquals(2L, checkoutDto.getAddressBookId());
    }

    @Test
    void setDeliveryOn() {
        Date date = new Date();
        checkoutDto.setDeliveryOn(date);
        assertEquals(date, checkoutDto.getDeliveryOn());
    }
}