package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;

import java.util.List;

public interface FullwidthDisplayFeaturedPromosService {

    List<FullwidthDisplayFeaturedPromosDto> getall();
    FullwidthDisplayFeaturedPromosDto add(FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto);
}