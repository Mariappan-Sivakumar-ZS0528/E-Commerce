package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.ReportDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface ReportService
{
     ReportDto addReport(Long id, ReportDto reportDto);
     List<ReportDto> getReportByTitle(Long id);
     ReportDto getById(Long id,Long reportTitleId);
     ReportDto updateReportDetails(Long id,Long reportTitleId, ReportDto reportDto);
     String deleteReport(Long id,Long reportTitleId);
     String downloadReport(HttpServletResponse response);
}
