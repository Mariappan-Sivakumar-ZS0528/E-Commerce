package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.ReportTitle;
import com.app.shopping.ecommerce.entity.ReportDetails;
import com.app.shopping.ecommerce.payload.ReportDto;
import com.app.shopping.ecommerce.repository.ReportTitleRepository;
import com.app.shopping.ecommerce.repository.ReportDetailsRepository;
import com.app.shopping.ecommerce.services.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService
{
    private ReportTitleRepository reportTitleRepository;
    private ReportDetailsRepository reportDetailsRepository;
    private ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    public ReportServiceImpl(ReportTitleRepository reportTitleRepository, ReportDetailsRepository reportDetailsRepository, ModelMapper modelMapper) {
        this.reportTitleRepository = reportTitleRepository;
        this.reportDetailsRepository = reportDetailsRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ReportDto addReport(Long id, ReportDto reportDto)
    {
        ReportDetails reportDetails = modelMapper.map(reportDto,ReportDetails.class);
        ReportTitle reportTitle = reportTitleRepository.findById(id).orElseThrow();
        reportDetails.setReportTitle(reportTitle);
        return modelMapper.map(reportDetailsRepository.save(reportDetails),ReportDto.class);
    }

    @Override
    public List<ReportDto> getReportByTitle(Long id)
    {
        List<ReportDetails> reportDetails = reportDetailsRepository.findByReportTitleId(id);
        if (reportDetails.isEmpty())
            return null;
        return reportDetails.stream().map(reportDetails1 -> modelMapper.map(reportDetails1,ReportDto.class)).toList();
    }

    @Override
    public ReportDto getById(Long id, Long reportTitleId) {
        ReportDetails reportDetails=reportDetailsRepository.findById(id).orElseThrow();
        if(!reportDetails.getReportTitle().getId().equals(reportTitleId))
            return null;
        return modelMapper.map(reportDetails,ReportDto.class);
    }

    @Override
    public ReportDto updateReportDetails(Long id, Long reportTitleId, ReportDto reportDto)
    {
        ReportDetails reportDetails=reportDetailsRepository.findById(id).orElseThrow();
        if(!reportDetails.getReportTitle().getId().equals(reportTitleId))
            return null;
        reportDetails.setName(reportDto.getName());
        ReportDetails updated=reportDetailsRepository.save(reportDetails);
        return modelMapper.map(updated,ReportDto.class);
    }

    @Override
    public String deleteReport(Long id, Long reportTitleId)
    {
        ReportDetails reportDetails=reportDetailsRepository.findById(id).orElseThrow();
        if(!reportDetails.getReportTitle().getId().equals(reportTitleId))
            return null;
        reportDetailsRepository.delete(reportDetails);
        return "Report deleted successfully";
    }

    @Override
    public String downloadReport(HttpServletResponse response) {
        List<ReportDetails> reportDetails=reportDetailsRepository.findAll();
        Workbook workbook= new HSSFWorkbook();
        Sheet sheet=workbook.createSheet("Report");
        Row row=sheet.createRow(0);
        row.createCell(0).setCellValue("Name");
        row.createCell(1).setCellValue("Column1");
        row.createCell(2).setCellValue("Column2");
        row.createCell(3).setCellValue("Column3");
        row.createCell(4).setCellValue("Column4");
        row.createCell(5).setCellValue("Column5");
        row.createCell(6).setCellValue("Column6");
        int rownum=1;
        for(ReportDetails reportDetail:reportDetails)
        {
            Row row1=sheet.createRow(rownum);
            row1.createCell(0).setCellValue(reportDetail.getName());
            row1.createCell(1).setCellValue(reportDetail.getColumn1());
            row1.createCell(2).setCellValue(reportDetail.getColumn2());
            row1.createCell(3).setCellValue(reportDetail.getColumn3());
            row1.createCell(4).setCellValue(reportDetail.getColumn4());
            row1.createCell(5).setCellValue(reportDetail.getColumn5());
            row1.createCell(6).setCellValue(reportDetail.getColumn6());
            rownum++;
        }
        try
        {
            workbook.write(response.getOutputStream());
            workbook.close();
        }
        catch (Exception e)
        {
            return "Error while downloading report{}" + e.getMessage();
        }
        return "Report downloaded successfully";
    }
}
