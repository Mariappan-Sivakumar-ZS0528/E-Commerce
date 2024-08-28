package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportDtoTest {
    ReportDto reportDto;
    @BeforeEach
    void setUp() {
        reportDto = new ReportDto(1L,"Report Details",1L);
    }

    @Test
    void getId() {
        assertEquals(1L,reportDto.getId());
    }

    @Test
    void getName() {
        assertEquals("Report Details",reportDto.getName());
    }

    @Test
    void getReportTitleId() {
        assertEquals(1L,reportDto.getReportTitleId());
    }

    @Test
    void setId() {
        reportDto.setId(2L);
        assertEquals(2L,reportDto.getId());
    }

    @Test
    void setName() {
        reportDto.setName("Report Details1");
        assertEquals("Report Details1",reportDto.getName());
    }

    @Test
    void setReportTitleId() {
        reportDto.setReportTitleId(2L);
        assertEquals(2L,reportDto.getReportTitleId());
    }
}