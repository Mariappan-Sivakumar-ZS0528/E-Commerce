package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Refund;
import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.RefundDto;
import com.app.shopping.ecommerce.repository.RefundRepository;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.services.RefundService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {
    private RefundRepository refundRepository;
    private ModelMapper modelMapper;
    private EmailExtractor emailExtractor;
    private SupplierRepository supplierRepository;

    public RefundServiceImpl(RefundRepository refundRepository, ModelMapper modelMapper, EmailExtractor emailExtractor, SupplierRepository supplierRepository) {
        this.refundRepository = refundRepository;
        this.modelMapper = modelMapper;
        this.emailExtractor = emailExtractor;
        this.supplierRepository = supplierRepository;
    }
    @Override
    public List<RefundDto> getBySupplier(HttpServletRequest request)
    {
        String email = emailExtractor.getEmailFromRequest(request);
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Supplier", "email", email));
        List<Refund> refund = refundRepository.findBySupplier(supplier);
        return refund.stream().map(refund1 -> modelMapper.map(refund1, RefundDto.class)).toList();
    }
    List <String> statusValue= Arrays.asList("PENDING", "APPROVED", "REJECTED");

    @Override
    public String statusUpdate(Long id, RefundDto refundDto)
    {
        Refund refund = refundRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Refund", "id", id));
        try
        {
            refund.setStatus(statusValue.get(refundDto.getStatus()));
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            return "Please press 0 for PENDING, 1 for APPROVED, 2 for REJECTED";
        }
        Refund savedRefund = refundRepository.save(refund);
        return "Refund is "+savedRefund.getStatus();
    }
}