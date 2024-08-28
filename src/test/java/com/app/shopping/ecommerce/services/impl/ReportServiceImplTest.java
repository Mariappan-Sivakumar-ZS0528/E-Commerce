package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.ReportDetails;
import com.app.shopping.ecommerce.entity.ReportTitle;
import com.app.shopping.ecommerce.payload.ReportDto;
import com.app.shopping.ecommerce.repository.ReportDetailsRepository;
import com.app.shopping.ecommerce.repository.ReportTitleRepository;
import com.app.shopping.ecommerce.services.ReportService;
import com.app.shopping.ecommerce.services.impl.ReportServiceImpl;
import jakarta.validation.executable.ValidateOnExecution;
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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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
    @Mock
    private ReportTitleRepository reportTitleRepository;
    @Mock
    private ModelMapper modelMapper;
    MockHttpServletRequest request = new MockHttpServletRequest();
    ReportDto reportDto;
    ReportTitle reportTitle;
    ReportDetails reportDetails;
    HttpServletResponse response=new MockHttpServletResponse();


    @BeforeEach
    public void setUp() {
        reportDto = new ReportDto();
        reportDto.setId(1L);
        reportDto.setName("Report Details");
         reportTitle = new ReportTitle();
        reportTitle.setId(1L);
        reportTitle.setTitle("Report Title");
        reportDetails = new ReportDetails();
        reportDetails.setId(1L);
        reportDetails.setReportTitle(reportTitle);
        reportDetails.setName("Report Details");
        List<ReportDetails> reportDetailsList = List.of(reportDetails);
        when(reportDetailsRepository.findAll()).thenReturn(reportDetailsList);
        when(reportDetailsRepository.save(Mockito.any(ReportDetails.class))).thenReturn(reportDetails);
        List<ReportTitle> reportTitles = List.of(reportTitle);
        when(reportTitleRepository.findById(1L)).thenReturn(Optional.of(reportTitle));
        when(reportTitleRepository.findAll()).thenReturn(reportTitles);
        when(reportDetailsRepository.findById(1L)).thenReturn(Optional.of(reportDetails));
        when(reportDetailsRepository.findByReportTitleId(1L)).thenReturn(List.of(reportDetails));
        when(reportTitleRepository.save(Mockito.any(ReportTitle.class))).thenReturn(reportTitle);
    }

    @Test
    public void testAddReport() throws IOException {
//        ReportDto reportDto = new ReportDto();
//        reportDto.setId(1L);
//        reportDto.setName("Report Details");
//        ReportDetails reportDetails = new ReportDetails();
//        reportDetails.setId(1L);
//        reportDetails.setName("Report Details");
        when(modelMapper.map(reportDto, ReportDetails.class)).thenReturn(reportDetails);
//        ReportTitle reportTitle = new ReportTitle();
//        reportTitle.setId(1L);
//        reportTitle.setTitle("Report Title");
//        reportTitle.setReportDetails(Set.of(reportDetails));
        when(reportTitleRepository.findById(1L)).thenReturn(Optional.of(reportTitle));
        when(reportDetailsRepository.save(reportDetails)).thenReturn(reportDetails);
        when(modelMapper.map(reportDetails, ReportDto.class)).thenReturn(reportDto);
        assertEquals(reportDto, reportService.addReport(1L, reportDto));
    }

    @Test
    public void testGetReportByTitle() {
        when(reportDetailsRepository.findByReportTitleId(1L)).thenReturn(List.of(reportDetails));
        when(modelMapper.map(reportDetails, ReportDto.class)).thenReturn(reportDto);
        assertEquals(List.of(reportDto),reportService.getReportByTitle(1L));


    }
    @Test
    void testGetReportByTitleEmpty() {
        List<ReportDto> reportDto = reportService.getReportByTitle(2L);
        assertEquals(null, reportDto);
    }

    @Test
    public void testGetById() {
        when(reportDetailsRepository.findById(1L)).thenReturn(Optional.of(reportDetails));
        when(modelMapper.map(reportDetails, ReportDto.class)).thenReturn(reportDto);
        assertEquals(reportDto, reportService.getById(1L, 1L));
    }

    @Test
    void testGetByIdEmpty() {
        ReportDetails reportDetails1 = new ReportDetails();
        reportDetails1.setId(2L);
        reportDetails1.setName("Report Details");
        reportDetails1.setReportTitle(new ReportTitle(2L, "Report Title",null));
        when(reportDetailsRepository.findById(2L)).thenReturn(Optional.of(reportDetails1));
        assertEquals(null, reportService.getById(2L, 1L));
    }
    @Test
    public void testUpdateReportDetails() throws IOException {
//        ReportDto reportDto = new ReportDto();
//        reportDto.setId(1L);
//        reportDto.setName("Report Details");
        when(reportDetailsRepository.findById(1L)).thenReturn(Optional.of(reportDetails));
        when(modelMapper.map(reportDetails, ReportDto.class)).thenReturn(reportDto);
        assertEquals(reportDto, reportService.updateReportDetails(1L, 1L, reportDto));
    }
    @Test
    void testUpdateReportDetailsEmpty() {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(1L);
        reportDto.setName("Report Details");
        ReportDto reportDto1 = reportService.updateReportDetails(1L, 2L, reportDto);
        assertEquals(null, reportDto1);
    }
    @Test
    public void testDeleteReport() {
        String reportDeleted = reportService.deleteReport(1L, 1L);
        assertEquals("Report deleted successfully", reportDeleted);
    }
    @Test
    public void testDeleteReportEmpty() {
        String reportDeleted = reportService.deleteReport(1L, 2L);
        assertEquals(null, reportDeleted);
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