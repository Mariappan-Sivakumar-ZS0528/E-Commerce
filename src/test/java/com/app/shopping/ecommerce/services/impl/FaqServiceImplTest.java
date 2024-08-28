package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Faq;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.FaqDto;
import com.app.shopping.ecommerce.repository.FaqRepository;
import com.app.shopping.ecommerce.services.FaqService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FaqServiceImplTest {
    @Autowired
    private FaqService faqService;

    @MockBean
    private FaqRepository faqRepository;

    @MockBean
    private ModelMapper modelMapper;

    Faq faq;
    FaqDto faqDto;

    @BeforeEach
    void setUp() {
        faq = new Faq(1L,"Question","Answer");
        faqDto = new FaqDto(1L,"Question","Answer");

        Mockito.when(faqRepository.save(Mockito.any(Faq.class))).thenReturn(faq);
        Mockito.when(modelMapper.map(Mockito.any(FaqDto.class), Mockito.any(Class.class))).thenReturn(faq);
        Mockito.when(faqRepository.findById(1L)).thenReturn(Optional.of(faq));
        Mockito.when(faqRepository.findAll()).thenReturn(List.of(faq));
        Mockito.when(modelMapper.map(faq, FaqDto.class)).thenReturn(faqDto);
        Mockito.when(faqRepository.existsById(1L)).thenReturn(true);
    }

    @Test
    void addFaq() {
        assertEquals(faqDto, faqService.addFaq(faqDto));
    }

    @Test
    void getFaqById() {
        assertEquals(faqDto, faqService.getFaqById(1L));
    }

    @Test
    void getAllFaq() {
        assertEquals(List.of(faqDto), faqService.getAllFaq());
    }

    @Test
    void updateFaq() {
        assertEquals(faqDto, faqService.updateFaq(1L, faqDto));
    }
    @Test
    void updateFaqFailure(){
        assertThrows(ResourceNotFoundException.class, () -> faqService.updateFaq(2L, faqDto));
    }

    @Test
    void deleteFaq() {
        assertEquals(true, faqService.deleteFaq(1L));
    }

    @Test
    void deleteFaqFailure(){
        assertEquals(false, faqService.deleteFaq(2L));
    }
}