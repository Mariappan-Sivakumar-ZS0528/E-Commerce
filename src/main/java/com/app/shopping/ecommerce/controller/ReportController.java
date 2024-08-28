package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.ReportDto;
import com.app.shopping.ecommerce.services.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/file")
public class ReportController
{
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/{id}/reportName")
    public ResponseEntity<ReportDto> addreportName(@PathVariable Long id, @RequestBody ReportDto reportDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.addReport(id,reportDto));
    }
    @GetMapping("/{id}/reportName")
    public ResponseEntity<List<ReportDto>> getReportByTitle(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getReportByTitle(id));
    }
    @GetMapping("/{id}/reportName/{reportDetailId}")
    public ResponseEntity<ReportDto> getById(@PathVariable Long id, @PathVariable Long reportDetailId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getById(reportDetailId,id));
    }
    @PutMapping("/{id}/reportName/{reportDetailId}")
    public ResponseEntity<ReportDto> updateReportDetails(@PathVariable Long id, @PathVariable Long reportDetailId, @RequestBody ReportDto reportDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.updateReportDetails(reportDetailId,id,reportDto));
    }
    @DeleteMapping("/{id}/reportName/{reportDetailId}")
    public ResponseEntity<String> deleteReport(@PathVariable Long id, @PathVariable Long reportDetailId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.deleteReport(reportDetailId,id));
    }

    @GetMapping("/download")
    public void downloadReport(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report.xls";
        response.setHeader(headerKey, headerValue);
        reportService.downloadReport(response);

    }
}
