package com.app.shopping.ecommerce;

import com.app.shopping.ecommerce.entity.ReportDetails;
import com.app.shopping.ecommerce.entity.ReportTitle;
import com.app.shopping.ecommerce.payload.ReportDto;
import com.app.shopping.ecommerce.repository.ReportDetailsRepository;
import com.app.shopping.ecommerce.repository.ReportTitleRepository;
import com.app.shopping.ecommerce.services.ReportService;
import com.app.shopping.ecommerce.services.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReportServiceImplTest {
    @Autowired
    private ReportService reportService;
    @MockBean
    private ReportDetailsRepository reportDetailsRepository;
    @MockBean
    private ReportTitleRepository reportTitleRepository;
    MockHttpServletRequest request = new MockHttpServletRequest();

    @BeforeEach
    public void setUp() {
        ReportTitle reportTitle = new ReportTitle();
        reportTitle.setId(1L);
        reportTitle.setTitle("Report Title");
        ReportDetails reportDetails = new ReportDetails();
        reportDetails.setId(1L);
        reportDetails.setReportTitle(reportTitle);
        reportDetails.setName("Report Details");
        List<ReportDetails> reportDetailsList = List.of(reportDetails);
        Mockito.when(reportDetailsRepository.findAll()).thenReturn(reportDetailsList);
        Mockito.when(reportDetailsRepository.save(Mockito.any(ReportDetails.class))).thenReturn(reportDetails);
        List<ReportTitle> reportTitles = List.of(reportTitle);
        Mockito.when(reportTitleRepository.findById(1L)).thenReturn(Optional.of(reportTitle));
        Mockito.when(reportTitleRepository.findAll()).thenReturn(reportTitles);
        Mockito.when(reportDetailsRepository.findById(1L)).thenReturn(Optional.of(reportDetails));
        Mockito.when(reportDetailsRepository.findByReportTitleId(1L)).thenReturn(List.of(reportDetails));
        Mockito.when(reportTitleRepository.save(Mockito.any(ReportTitle.class))).thenReturn(reportTitle);
    }

    @Test
    public void testAddReport() throws IOException {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(1L);
        reportDto.setName("Report Details");
        ReportDto reportDto1 = reportService.addReport(1L, reportDto);
        assertEquals("Report Details", reportDto1.getName());
    }

    @Test
    public void testGetReportByTitle() {


        List<ReportDto> reportDto = reportService.getReportByTitle(1L);
        assertEquals("Report Details", reportDto.get(0).getName());

    }

    @Test
    public void testGetById() {
        ReportDto reportDto = reportService.getById(1L, 1L);
        assertEquals("Report Details", reportDto.getName());
        assertEquals(1L, reportDto.getId());
    }

    @Test
    public void testUpdateReportDetails() throws IOException {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(1L);
        reportDto.setName("Report Details");
        ReportDto reportDto1 = reportService.updateReportDetails(1L, 1L, reportDto);
        assertEquals("Report Details", reportDto1.getName());
    }
    @Test
    public void testDeleteReport() {
        String reportDeleted = reportService.deleteReport(1L, 1L);
        assertEquals("Report deleted successfully", reportDeleted);
    }
}