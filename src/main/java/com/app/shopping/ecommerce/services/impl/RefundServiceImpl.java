package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Refund;
import com.app.shopping.ecommerce.payload.RefundDto;
import com.app.shopping.ecommerce.repository.RefundRepository;
import com.app.shopping.ecommerce.services.RefundService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {
    private RefundRepository refundRepository;
    private ModelMapper modelMapper;

    @Override
    public List<RefundDto> getAllByMerchantId()
    {
        List<Refund> refunds = refundRepository.findAll();
        if (refunds.isEmpty())
            return null;
        return refunds.stream().map(refund -> modelMapper.map(refund, RefundDto.class)).toList();
    }
}
