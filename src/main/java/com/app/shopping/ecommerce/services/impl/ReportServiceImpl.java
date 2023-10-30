package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.ReportTitle;
import com.app.shopping.ecommerce.entity.ReportDetails;
import com.app.shopping.ecommerce.payload.ReportDto;
import com.app.shopping.ecommerce.repository.ReportTitleRepository;
import com.app.shopping.ecommerce.repository.ReportDetailsRepository;
import com.app.shopping.ecommerce.services.ReportService;
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
}
