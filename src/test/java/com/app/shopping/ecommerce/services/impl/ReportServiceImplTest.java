package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.ReportDetails;
import com.app.shopping.ecommerce.entity.ReportTitle;
import com.app.shopping.ecommerce.repository.ReportDetailsRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {
    @InjectMocks
    private ReportServiceImpl reportService;
    @Mock
    private ReportDetailsRepository reportDetailsRepository;
    List<ReportDetails> reportDetails;
    HttpServletResponse response=new MockHttpServletResponse();

    @Mock
    private Logger logger;
    @BeforeEach
    void setUp() {
        ReportDetails reportDetails1=new ReportDetails(1L,"name",new ReportTitle(),"column1","column2","column3","column4","column5","column6");
        reportDetails=List.of(reportDetails1);
        Mockito.when(reportDetailsRepository.findAll()).thenReturn(reportDetails);
    }

    @Test
    void downloadReport() {
        assertEquals("Report downloaded successfully", reportService.downloadReport(response));
    }

    @Test
    void downloadReport_Throws() throws IOException {
        response=mock(HttpServletResponse.class);
        Mockito.when(response.getOutputStream()).thenThrow(IOException.class);
        String result = reportService.downloadReport(response);
        assertTrue(result.contains("Error while downloading report"));
    }
}