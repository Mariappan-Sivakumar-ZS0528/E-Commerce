package com.app.shopping.ecommerce.testcontroller;

import com.app.shopping.ecommerce.controller.FaqController;
import com.app.shopping.ecommerce.payload.FaqDto;
import com.app.shopping.ecommerce.services.FaqService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FaqControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FaqService faqService;

    @InjectMocks
    private FaqController faqController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(faqController).build();
    }

    @Test
    public void testAddFaq() {
        FaqDto newFaq = new FaqDto(); // Initialize with your data

        when(faqService.addFaq(newFaq)).thenReturn(newFaq);

        ResponseEntity<FaqDto> response = faqController.addFaq(newFaq);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(newFaq, response.getBody());
    }

    @Test
    public void testGetAllFaq() {
        List<FaqDto> faqList = List.of(new FaqDto(), new FaqDto()); // Initialize with your data

        when(faqService.getAllFaq()).thenReturn(faqList);

        ResponseEntity<List<FaqDto>> response = faqController.getAllFaq();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(faqList, response.getBody());
    }

    @Test
    public void testGetFaqById() {
        Long faqId = 1L; // Specify the ID you want to test

        FaqDto faqDto = new FaqDto(); // Initialize with your data

        when(faqService.getFaqById(faqId)).thenReturn(faqDto);

        ResponseEntity<FaqDto> response = faqController.getFaqById(faqId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(faqDto, response.getBody());
    }

    // Similar tests for updateFaq and deleteFaq can be added
}
