package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.ReportDto;
import com.app.shopping.ecommerce.services.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@Tag(name = "Report", description = " CRUD Operations related to File")
public class ReportController
{
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    @Operation(summary = "Upload Report")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{id}/reportName")
    public ResponseEntity<ReportDto> addreportName(@PathVariable Long id, @RequestBody ReportDto reportDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.addReport(id,reportDto));
    }
    @Operation(summary = "Get Report By Title")
    @GetMapping("/{id}/reportName")
    public ResponseEntity<List<ReportDto>> getReportByTitle(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getReportByTitle(id));
    }
    @Operation(summary = "Get Report By DetailId")
    @GetMapping("/{id}/reportName/{reportDetailId}")
    public ResponseEntity<ReportDto> getById(@PathVariable Long id, @PathVariable Long reportDetailId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getById(reportDetailId,id));
    }
    @Operation(summary = "Update Report Details")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/reportName/{reportDetailId}")
    public ResponseEntity<ReportDto> updateReportDetails(@PathVariable Long id, @PathVariable Long reportDetailId, @RequestBody ReportDto reportDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.updateReportDetails(reportDetailId,id,reportDto));
    }
    @Operation(summary = "Delete Report")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}/reportName/{reportDetailId}")
    public ResponseEntity<String> deleteReport(@PathVariable Long id, @PathVariable Long reportDetailId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.deleteReport(reportDetailId,id));
    }
}
