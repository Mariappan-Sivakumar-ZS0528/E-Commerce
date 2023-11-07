package com.app.shopping.ecommerce.testcontroller;
import com.app.shopping.ecommerce.entity.Faq;
import com.app.shopping.ecommerce.repository.FaqRepository;
import com.app.shopping.ecommerce.services.FaqService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringRunner.class)
@SpringBootTest
public class FaqControllerTest {
    @Autowired
    private FaqService faqService;
    @MockBean
    private FaqRepository faqRepository;
    @Test
    public void getAllTest(){
        when(faqRepository.findAll()).thenReturn(List.of(
                new Faq(1L, "What is FAQ", "FAQ is Frequently Asked Question"),
                new Faq(2L, "What is our benefits?", "Helps for many reasons")
        ));
    assertEquals(2,faqService.getAllFaq().size());
    }

    }



