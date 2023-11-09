package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface FullwidthDisplayFeaturedPromosService {

    List<FullwidthDisplayFeaturedPromosDto> getall();
    String uploadImages (Long id, byte[] mobileImage , byte[] desktopImage) throws IOException;
    FullwidthDisplayFeaturedPromosDto add(FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto);

    byte[] downloadMobileImage(Long id);

    byte[] downloadDesktopImage(Long id);

    String getMeasurement(Long id,String WhichImageMeasurements);

    String deletePromos(Long id);

    String disablePromos(Long id);

    String enablePromos(Long id);

    FullwidthDisplayFeaturedPromosDto updateWholePromos(Long id, FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto);
}