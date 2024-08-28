package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Legal;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.LegalDto;
import com.app.shopping.ecommerce.repository.LegalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class LegalServiceImplTest {
    @Mock
    private LegalRepository legalRepository;

    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private LegalServiceImpl legalService;
    LegalDto legalDto = new LegalDto();
    Legal legal = new Legal();
    @BeforeEach
    void setupService() {
        legalDto.setId(1L);
        legalDto.setTitle("title");
        legalDto.setContent("content");
        legal.setContent("content");
        legal.setTitle("title");
        legal.setId(1L);
    }

    @Test
    void addLegal() {
        when(modelMapper.map(legalDto, Legal.class)).thenReturn(legal);
        when(modelMapper.map(legal, LegalDto.class)).thenReturn(legalDto);
        when(legalRepository.save(legal)).thenReturn(legal);
        assertEquals(legalService.addLegal(legalDto), legalDto);
    }

    @Test
    void getLegalByIdForSuccess() {
        when(legalRepository.findById(1L)).thenReturn(java.util.Optional.of(legal));
        when(modelMapper.map(legal, LegalDto.class)).thenReturn(legalDto);
        LegalDto legalDto1 = legalService.getLegalById(1L);
        assertEquals(legalDto, legalDto1);
    }
    @Test
    void getLegalByIdForFailure() {

        // Mocking repository to return empty Optional
        when(legalRepository.findById(2L)).thenReturn(Optional.empty());
        when(modelMapper.map(legal, LegalDto.class)).thenReturn(legalDto);
        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> {
            legalService.getLegalById(2L);
        });

    }

    @Test
    void getAllLegal() {
        when(legalRepository.findAll()).thenReturn(List.of(legal));
        assertEquals(legalService.getAllLegal(), List.of(legal));
    }

    @Test
    void updateLegalForSuccess() {
        when(legalRepository.findById(1L)).thenReturn(Optional.of(legal));
        when(modelMapper.map(legalDto, Legal.class)).thenReturn(legal);
        when(legalRepository.save(legal)).thenReturn(legal);
        when(modelMapper.map(legal, LegalDto.class)).thenReturn(legalDto);
        assertEquals(legalService.updateLegal(1L,legalDto), legalDto);
    }

    @Test
    void updateLegalForFailure(){
        when(legalRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> legalService.updateLegal(2L,legalDto));
    }
    @Test
    void deleteLegalIfSuccess() {
        when(legalRepository.existsById(1L)).thenReturn(true);
        assertEquals(legalService.deleteLegal(1L), true);
    }
    @Test
    void deleteLegalIfFailure(){
        when(legalRepository.existsById(2L)).thenReturn(false);
        assertEquals(legalService.deleteLegal(2L), false);
    }
}