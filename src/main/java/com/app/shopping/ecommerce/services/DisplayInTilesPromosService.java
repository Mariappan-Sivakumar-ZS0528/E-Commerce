package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.DisplayInTilesPromosDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface DisplayInTilesPromosService {
    List<DisplayInTilesPromosDto> getall();

    DisplayInTilesPromosDto add(DisplayInTilesPromosDto displayInTilesPromosDto);

    byte[] downloadMobileImage(Long id);

    byte[] downloadDesktopImage(Long id);

    String getMeasurement(Long id, String WhichImageMeasurements);

    String deletePromos(Long id);

    String disablePromos(Long id);

    String enablePromos(Long id);

    DisplayInTilesPromosDto updateWholePromos(Long id, DisplayInTilesPromosDto displayInTilesPromosDto);

    String uploadImages(Long id, byte[] mobileImage, byte[] desktopImage) throws IOException;
}
