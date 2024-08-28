package com.app.shopping.ecommerce.entity;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReportTitleTest {
    ReportTitle reportTitle;
    @BeforeEach
    void setUp() {
        reportTitle = new ReportTitle(1L, "Report Title", Set.of(new ReportDetails()));
    }

    @Test
    void getId() {
        assertEquals(1L, reportTitle.getId());
    }

    @Test
    void getTitle() {
        assertEquals("Report Title", reportTitle.getTitle());
    }

    @Test
    void getReportDetails() {
        Set<ReportDetails> reportDetails = Set.of(new ReportDetails());
        reportTitle.setReportDetails(reportDetails);
        assertEquals(reportDetails, reportTitle.getReportDetails());
    }

    @Test
    void setId() {
        reportTitle.setId(2L);
        assertEquals(2L, reportTitle.getId());
    }

    @Test
    void setTitle() {
        reportTitle.setTitle("Report Title 2");
        assertEquals("Report Title 2", reportTitle.getTitle());
    }

    @Test
    void setReportDetails() {
        Set<ReportDetails> reportDetails = Set.of(new ReportDetails());
        reportTitle.setReportDetails(reportDetails);
        assertEquals(reportDetails, reportTitle.getReportDetails());
    }

    @Test
    void testEquals() {
        ReportTitle reportTitle1 = new ReportTitle(1L, "Report Title", Set.of(new ReportDetails()));
        ReportTitle reportTitle2 = new ReportTitle(1L, "Report Title", Set.of(new ReportDetails()));
        assertEquals(reportTitle1, reportTitle2);
    }

    @Test
    void canEqual() {
        ReportTitle reportTitle1 = new ReportTitle(1L, "Report Title", Set.of(new ReportDetails()));
        ReportTitle reportTitle2 = new ReportTitle(2L, "Report Title", Set.of(new ReportDetails()));
        assertNotEquals(reportTitle1, reportTitle2);
    }

    @Test
    void testHashCode() {
        ReportTitle reportTitle1 = new ReportTitle(1L, "Report Title", Set.of(new ReportDetails()));
        ReportTitle reportTitle2 = new ReportTitle(1L, "Report Title", Set.of(new ReportDetails()));
        assertEquals(reportTitle1.hashCode(), reportTitle2.hashCode());
    }

    @Test
    void testToString() {
        ReportTitle reportTitle1 = new ReportTitle(1L, "Report Title", Set.of(new ReportDetails()));
        assertEquals("ReportTitle(id=1, title=Report Title, reportDetails=[ReportDetails(id=null, name=null, reportTitle=null)])", reportTitle1.toString());
    }

    @Test
    void builder() {
        ReportTitle reportTitle1 = ReportTitle.builder()
                .id(1L)
                .title("Report Title")
                .reportDetails(Set.of(new ReportDetails()))
                .build();
        assertEquals(1L, reportTitle1.getId());
        assertEquals("Report Title", reportTitle1.getTitle());
        assertEquals(Set.of(new ReportDetails()), reportTitle1.getReportDetails());
    }
}