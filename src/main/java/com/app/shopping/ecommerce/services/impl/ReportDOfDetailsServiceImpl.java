package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.ReportDOfDetails;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.ReportDOfDetailsDto;
import com.app.shopping.ecommerce.repository.ReportDOfDetailsRepository;
import com.app.shopping.ecommerce.services.ReportDOfDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ReportDOfDetailsServiceImpl implements ReportDOfDetailsService {
private ReportDOfDetailsRepository reportDOfDetailsRepository;
private ModelMapper modelMapper;

    public ReportDOfDetailsServiceImpl(ReportDOfDetailsRepository reportDOfDetailsRepository, ModelMapper modelMapper) {
        this.reportDOfDetailsRepository = reportDOfDetailsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReportDOfDetailsDto getReportDOfDetailsById(Long id)
    {
//        modelMapper.map(reportDOfDetailsRepository.findById(id).orElseThrow(), ReportDOfDetailsDto.class);
        ReportDOfDetails reportDOfDetails = reportDOfDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ReportDOfDetails", "id", id));
        if (reportDOfDetails != null)
        {
            return modelMapper.map(reportDOfDetails, ReportDOfDetailsDto.class);
        }
        return null;
    }
}
