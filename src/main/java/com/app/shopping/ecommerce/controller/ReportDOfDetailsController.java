package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.entity.ReportDOfDetails;
import com.app.shopping.ecommerce.payload.ReportDOfDetailsDto;
import com.app.shopping.ecommerce.services.ReportDOfDetailsService;
import com.app.shopping.ecommerce.services.impl.ReportDOfDetailsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/report-details")
@Tag(name = "ReportDOfDetails Controller", description = "APIs for ReportDOfDetails")

public class ReportDOfDetailsController
{
    private ReportDOfDetailsService ReportDOfDetailsService;

    public ReportDOfDetailsController(com.app.shopping.ecommerce.services.ReportDOfDetailsService reportDOfDetailsService) {
        ReportDOfDetailsService = reportDOfDetailsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ReportDOfDetailsDto>> reportDOfDetailsController(@PathVariable(value = "id")Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(ReportDOfDetailsService.getReportDOfDetailsById(id)));
    }
}
