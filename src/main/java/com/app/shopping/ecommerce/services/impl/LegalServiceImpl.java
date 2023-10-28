package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Legal;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.LegalDto;
import com.app.shopping.ecommerce.repository.LegalRepository;
import com.app.shopping.ecommerce.services.LegalService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegalServiceImpl implements LegalService {
    private final LegalRepository legalRepository;
    private final ModelMapper modelMapper;
    Logger logger= LoggerFactory.getLogger(LegalServiceImpl.class);

    @Autowired
    public LegalServiceImpl(LegalRepository legalRepository, ModelMapper modelMapper) {
        this.legalRepository = legalRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public LegalDto addlegal(LegalDto newLegal) {
        Legal legal = modelMapper.map(newLegal, Legal.class);
        return modelMapper.map(legalRepository.save(legal), LegalDto.class);
    }

    @Override
    public LegalDto addLegal(LegalDto newLegal) {
        Legal legal = modelMapper.map(newLegal, Legal.class);
        return modelMapper.map(legalRepository.save(legal), LegalDto.class);
    }

    @Override
    public LegalDto getLegalById(Long id) {
        Legal legal = legalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Legal", "id", id));
        return modelMapper.map(legal, LegalDto.class);
    }

    @Override
    public List<Legal> getAllLegal() {
        return legalRepository.findAll().stream().map(legal-> modelMapper.map(legal, Legal.class)).toList();
    }

    @Override
    public LegalDto updateLegal(Long id, LegalDto updatedLegal) {
        Optional<Legal> existingLegal= legalRepository.findById(id);
        if (existingLegal.isPresent()) {
            updatedLegal.setId(id);
            Legal legal = modelMapper.map(updatedLegal, Legal.class);
            return modelMapper.map(legalRepository.save(legal), LegalDto.class);
        }
        else {
            throw new ResourceNotFoundException("Legal", "id", id);
        }
    }

    @Override
    public boolean deleteLegal(Long id) {
        if(legalRepository.existsById(id)){
            legalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
