package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.FullwidthDisplayFeaturedPromos;
import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;
import com.app.shopping.ecommerce.repository.FullwidthDisplayFeaturedPromosRepository;
import com.app.shopping.ecommerce.services.FullwidthDisplayFeaturedPromosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FullwidthDisplayFeaturedPromosServiceImpl implements FullwidthDisplayFeaturedPromosService {
    private FullwidthDisplayFeaturedPromosRepository fullwidthDisplayFeaturedPromosRepository;
    private ModelMapper modelMapper;
    public FullwidthDisplayFeaturedPromosServiceImpl(FullwidthDisplayFeaturedPromosRepository fullwidthDisplayFeaturedPromosRepository,ModelMapper modelMapper){
        this.fullwidthDisplayFeaturedPromosRepository=fullwidthDisplayFeaturedPromosRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public List<FullwidthDisplayFeaturedPromosDto> getall() {
        List<FullwidthDisplayFeaturedPromos> fullwidthDisplayFeaturedPromos=fullwidthDisplayFeaturedPromosRepository.findAll();
        return fullwidthDisplayFeaturedPromos.stream().map(fullwidthDisplayFeaturedPromos1 -> modelMapper.map(fullwidthDisplayFeaturedPromos1,FullwidthDisplayFeaturedPromosDto.class)).toList();
    }

    @Override
    public FullwidthDisplayFeaturedPromosDto add(FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto) {
        FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos=modelMapper.map(fullwidthDisplayFeaturedPromosDto,FullwidthDisplayFeaturedPromos.class);
        FullwidthDisplayFeaturedPromos updatedFullwidthDisplayFeaturedPromos=fullwidthDisplayFeaturedPromosRepository.save(fullwidthDisplayFeaturedPromos);
        return modelMapper.map(updatedFullwidthDisplayFeaturedPromos,FullwidthDisplayFeaturedPromosDto.class);
    }
}
