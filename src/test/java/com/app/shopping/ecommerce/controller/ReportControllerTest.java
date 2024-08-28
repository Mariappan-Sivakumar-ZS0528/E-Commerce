package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.ReportDto;
import com.app.shopping.ecommerce.services.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ReportControllerTest {
    @Mock
    private ReportService reportService;
    @InjectMocks
    private ReportController reportController;
    @Test
    void addreportName() {
        ReportDto reportDto = new ReportDto();
        when(reportService.addReport(1L,reportDto)).thenReturn(reportDto);
        ReportDto actual = reportController.addreportName(1L,reportDto).getBody();
        assertEquals(reportDto,actual);
    }

    @Test
    void getReportByTitle() {
        ReportDto reportDto = new ReportDto();
        when(reportService.getReportByTitle(1L)).thenReturn(List.of(reportDto));
        List<ReportDto> actual = reportController.getReportByTitle(1L).getBody();
        assertEquals(List.of(reportDto),actual);
    }

    @Test
    void getById() {
        ReportDto reportDto = new ReportDto();
        when(reportService.getById(1L,1L)).thenReturn(reportDto);
        ReportDto actual = reportController.getById(1L,1L).getBody();
        assertEquals(reportDto,actual);
    }

    @Test
    void updateReportDetails() {
        ReportDto reportDto = new ReportDto();
        when(reportService.updateReportDetails(1L,1L,reportDto)).thenReturn(reportDto);
        ReportDto actual = reportController.updateReportDetails(1L,1L,reportDto).getBody();
        assertEquals(reportDto,actual);
    }

    @Test
    void deleteReport() {
        when(reportService.deleteReport(1L,1L)).thenReturn("Success");
        String actual = reportController.deleteReport(1L,1L).getBody();
        assertEquals("Success",actual);
    }
}