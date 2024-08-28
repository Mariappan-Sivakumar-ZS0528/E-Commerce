package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FullwidthDisplayFeaturedPromosService {

    List<FullwidthDisplayFeaturedPromosDto> getall();
    String uploadImages (Long id, byte[] mobileImage , byte[] desktopImage) throws IOException;
    FullwidthDisplayFeaturedPromosDto add(FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto);

    Optional<byte[]> downloadMobileImage(Long id);

    Optional<byte[]> downloadDesktopImage(Long id);

    String getMeasurement(Long id,String WhichImageMeasurements);

    String deletePromos(Long id);

    String disablePromos(Long id);

    String enablePromos(Long id);

    Optional<FullwidthDisplayFeaturedPromosDto> updateWholePromos(Long id, FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto);
}