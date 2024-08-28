package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.payload.FaqDto;
import com.app.shopping.ecommerce.services.FaqService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FaqControllerTest {
    @Autowired
    FaqController faqController;
    @MockBean
    FaqService faqService;

    FaqDto faqDto;

    @BeforeEach
    void setUp() {
        faqDto=new FaqDto();
        Mockito.when(faqService.addFaq(faqDto)).thenReturn(faqDto);
        Mockito.when(faqService.getFaqById(1L)).thenReturn(faqDto);
        Mockito.when(faqService.getAllFaq()).thenReturn(List.of(faqDto));
        Mockito.when(faqService.updateFaq(1L,faqDto)).thenReturn(faqDto);
        Mockito.when(faqService.deleteFaq(1L)).thenReturn(true);
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void addFaq() {
        ResponseEntity<FaqDto> responseEntity = faqController.addFaq(faqDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(faqDto, responseEntity.getBody());
    }

    @Test
    void getAllFaq() {
        ResponseEntity<List<FaqDto>> responseEntity = faqController.getAllFaq();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(List.of(faqDto), responseEntity.getBody());
    }

    @Test
    void getFaqById() {
        ResponseEntity<FaqDto> responseEntity = faqController.getFaqById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(faqDto, responseEntity.getBody());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateFaq() {
        ResponseEntity<FaqDto> responseEntity = faqController.updateFaq(1L,faqDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(faqDto, responseEntity.getBody());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteFaq() {
        ResponseEntity<Boolean> responseEntity = faqController.deleteFaq(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }
}