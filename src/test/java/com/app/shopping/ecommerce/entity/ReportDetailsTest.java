package com.app.shopping.ecommerce.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportDetailsTest {
    ReportDetails reportDetails;
    @BeforeEach
    void setUp() {
        reportDetails = new ReportDetails(1L,"name",new ReportTitle());
    }

    @Test
    void getId() {
        assertEquals(1L,reportDetails.getId());
    }

    @Test
    void getName() {
        assertEquals("name",reportDetails.getName());
    }

    @Test
    void getReportTitle() {
        assertEquals(new ReportTitle(),reportDetails.getReportTitle());
    }

    @Test
    void setId() {
        reportDetails.setId(2L);
        assertEquals(2L,reportDetails.getId());
    }

    @Test
    void setName() {
        reportDetails.setName("name2");
        assertEquals("name2",reportDetails.getName());
    }

    @Test
    void setReportTitle() {
        reportDetails.setReportTitle(new ReportTitle());
        assertEquals(new ReportTitle(),reportDetails.getReportTitle());
    }

    @Test
    void testEquals() {
        assertTrue(reportDetails.equals(reportDetails));
    }

    @Test
    void canEqual() {
        assertTrue(reportDetails.canEqual(reportDetails));
    }

    @Test
    void testHashCode() {
        ReportTitle reportTitle = new ReportTitle();
        ReportDetails reportDetails1 = new ReportDetails(1L,"name",reportTitle);
        reportDetails.setReportTitle(reportTitle);
        assertEquals(reportDetails1.hashCode(),reportDetails.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(reportDetails.toString(),reportDetails.toString());
    }

    @Test
    void builder() {
        ReportDetails reportDetails = ReportDetails.builder().id(1L).name("name").reportTitle(new ReportTitle()).build();
        assertEquals(1L,reportDetails.getId());
        assertEquals("name",reportDetails.getName());
        assertEquals(new ReportTitle(),reportDetails.getReportTitle());
    }
}