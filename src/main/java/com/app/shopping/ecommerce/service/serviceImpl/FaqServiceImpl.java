package com.app.shopping.ecommerce.service.serviceImpl;

import com.app.shopping.ecommerce.entity.Faq;
import com.app.shopping.ecommerce.payload.FaqDto;
import com.app.shopping.ecommerce.repository.FaqRepository;
import com.app.shopping.ecommerce.service.FaqService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaqServiceImpl implements FaqService {
    private final FaqRepository faqRepository;
    private final ModelMapper modelMapper;

@Autowired
    public FaqServiceImpl(FaqRepository faqRepository, ModelMapper modelMapper) {
        this.faqRepository = faqRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FaqDto addFaq(FaqDto newFaq) {
        Faq faq = modelMapper.map(newFaq, Faq.class);
        return modelMapper.map(faqRepository.save(faq), FaqDto.class);
    }

    @Override
    public FaqDto getFaqById(Long id) {
        Faq faq = faqRepository.findById(id).orElse(null);
        return modelMapper.map(faq, FaqDto.class);
    }

    @Override
    public List<FaqDto> getAllFaq() {
        return faqRepository.findAll().stream().map(faq -> modelMapper.map(faq, FaqDto.class)).toList();
    }

    @Override
    public FaqDto updateFaq(Long id, FaqDto updatedFaq) {
        Optional<Faq> existingFaq = faqRepository.findById(id);
        if (existingFaq.isPresent()) {
            updatedFaq.setId(id);
            Faq faq = modelMapper.map(updatedFaq, Faq.class);
            return modelMapper.map(faqRepository.save(faq), FaqDto.class);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean deleteFaq(Long id) {
    if(faqRepository.existsById(id)){
        faqRepository.deleteById(id);
        return true;
    }
        return false;
    }
}
