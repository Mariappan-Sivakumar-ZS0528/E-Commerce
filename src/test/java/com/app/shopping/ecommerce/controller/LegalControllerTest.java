package com.app.shopping.ecommerce.controller;

import com.app.shopping.ecommerce.entity.Legal;
import com.app.shopping.ecommerce.payload.LegalDto;
import com.app.shopping.ecommerce.services.LegalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class LegalControllerTest {
    @Mock
    private LegalService legalService;
    @InjectMocks
    private LegalController legalController;
    @Test
    void addLegal() {
        LegalDto newLegal = new LegalDto();
        when(legalService.addLegal(newLegal)).thenReturn(newLegal);
        LegalDto actual = legalController.addLegal(newLegal).getBody();
        assertEquals(newLegal, actual);
    }

    @Test
    void getAllLegal() {
        Legal newLegal = new Legal();
        when(legalService.getAllLegal()).thenReturn(List.of(newLegal));
        List<Legal> actual = legalController.getAllLegal().getBody();
        assertEquals(List.of(newLegal), actual);
    }

    @Test
    void getLegalById() {
        LegalDto newLegal = new LegalDto();
        when(legalService.getLegalById(1L)).thenReturn(newLegal);
        LegalDto actual = legalController.getLegalById(1L).getBody();
        assertEquals(newLegal, actual);
    }

    @Test
    void updateLegal() {
        LegalDto updatedLegal = new LegalDto();
        when(legalService.updateLegal(1L, updatedLegal)).thenReturn(updatedLegal);
        LegalDto actual = legalController.updateLegal(1L, updatedLegal).getBody();
        assertEquals(updatedLegal, actual);
    }

    @Test
    void deleteLegal() {
        when(legalService.deleteLegal(1L)).thenReturn(true);
        assertEquals(legalController.deleteLegal(1L).getBody(), true);
    }
}