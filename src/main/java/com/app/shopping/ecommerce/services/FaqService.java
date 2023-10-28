package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.entity.Faq;
import com.app.shopping.ecommerce.payload.FaqDto;

import java.util.List;

public interface FaqService {
    FaqDto addFaq(FaqDto newFaq);
    FaqDto getFaqById(Long id);
    List<FaqDto> getAllFaq();

    FaqDto updateFaq(Long id, FaqDto updatedFaq);
    boolean deleteFaq(Long id);
}
