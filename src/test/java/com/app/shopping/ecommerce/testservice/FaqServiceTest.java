package com.app.shopping.ecommerce.testservice;
import com.app.shopping.ecommerce.entity.Faq;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.FaqDto;
import com.app.shopping.ecommerce.repository.FaqRepository;
import com.app.shopping.ecommerce.services.impl.FaqServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
@SpringBootTest
public class FaqServiceTest {

        @InjectMocks
        private FaqServiceImpl faqService;

        @MockBean
        private FaqRepository faqRepository;

        @Mock
        private ModelMapper modelMapper;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testAddFaq() {
            FaqDto newFaqDto = new FaqDto(1L,"What is FAQ?","Frequently Asked Questions");
            Faq newFaq = new Faq();

            Mockito.when(modelMapper.map(newFaqDto, Faq.class)).thenReturn(newFaq);
            Mockito.when(faqRepository.save(newFaq)).thenReturn(newFaq);

            FaqDto result = faqService.addFaq(newFaqDto);

            assertNotNull(result);
            assertEquals(newFaqDto, result);
        }

        @Test
        void testGetFaqById() {
            Long id = 1L;
            Faq faq = new Faq();
            FaqDto faqDto = new FaqDto();

            Mockito.when(faqRepository.findById(id)).thenReturn(Optional.of(faq));
            Mockito.when(modelMapper.map(faq, FaqDto.class)).thenReturn(faqDto);

            FaqDto result = faqService.getFaqById(id);

            assertNotNull(result);
            assertEquals(faqDto, result);
        }

        @Test
        void testGetFaqById_NotFound() {
            Long id = 1L;

            Mockito.when(faqRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> faqService.getFaqById(id));
        }

        @Test
        void testGetAllFaq() {
            List<Faq> faqList = new ArrayList<>();
            List<FaqDto> faqDtoList = new ArrayList<>();

            Mockito.when(faqRepository.findAll()).thenReturn(faqList);
            Mockito.when(modelMapper.map(any(), any())).thenReturn(faqDtoList);

            List<FaqDto> result = faqService.getAllFaq();

            assertNotNull(result);
            assertEquals(faqDtoList, result);
        }

        @Test
        void testUpdateFaq() {
            Long id = 1L;
            FaqDto updatedFaqDto = new FaqDto();
            Faq updatedFaq = new Faq();
            Optional<Faq> existingFaq = Optional.of(new Faq());

            Mockito.when(faqRepository.findById(id)).thenReturn(existingFaq);
            Mockito.when(modelMapper.map(updatedFaqDto, Faq.class)).thenReturn(updatedFaq);
            Mockito.when(faqRepository.save(updatedFaq)).thenReturn(updatedFaq);

            FaqDto result = faqService.updateFaq(id, updatedFaqDto);

            assertNotNull(result);
            assertEquals(updatedFaqDto, result);
        }

        @Test
        void testUpdateFaq_NotFound() {
            Long id = 1L;
            FaqDto updatedFaqDto = new FaqDto();

            Mockito.when(faqRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> faqService.updateFaq(id, updatedFaqDto));
        }

        @Test
        void testDeleteFaq() {
            Long id = 1L;

            Mockito.when(faqRepository.existsById(id)).thenReturn(true);

            boolean result = faqService.deleteFaq(id);

            assertTrue(result);
        }

        @Test
        void testDeleteFaq_NotFound() {
            Long id = 1L;

            Mockito.when(faqRepository.existsById(id)).thenReturn(false);

            boolean result = faqService.deleteFaq(id);

            assertFalse(result);
        }
    }


