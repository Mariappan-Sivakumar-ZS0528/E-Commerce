package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.DisplayInTilesPromos;
import com.app.shopping.ecommerce.payload.DisplayInTilesPromosDto;
import com.app.shopping.ecommerce.payload.ImageMeasurementsDto;
import com.app.shopping.ecommerce.repository.DisplayInTilesPromosRepository;
import com.app.shopping.ecommerce.services.DisplayInTilesPromosService;
import com.app.shopping.ecommerce.util.ImageDimensionExtractor;
import com.app.shopping.ecommerce.util.ImageDimensions;
import com.app.shopping.ecommerce.util.ImageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class DisplayInTilesPromosServiceImpl implements DisplayInTilesPromosService {
    private DisplayInTilesPromosRepository displayInTilesPromosRepository;
    private ModelMapper modelMapper;
    public DisplayInTilesPromosServiceImpl(DisplayInTilesPromosRepository displayInTilesPromosRepository,ModelMapper modelMapper){
        this.displayInTilesPromosRepository=displayInTilesPromosRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public List<DisplayInTilesPromosDto> getall() {
        List<DisplayInTilesPromos> displayInTilesPromos=displayInTilesPromosRepository.findAll();
        return displayInTilesPromos.stream().map(displayInTilesPromos1 -> modelMapper.map(displayInTilesPromos1,DisplayInTilesPromosDto.class)).toList();
    }

    @Override
    public DisplayInTilesPromosDto add(DisplayInTilesPromosDto displayInTilesPromosDto) {
        DisplayInTilesPromos displayInTilesPromos=modelMapper.map(displayInTilesPromosDto,DisplayInTilesPromos.class);
        DisplayInTilesPromos updatedDisplayInTilesPromos=displayInTilesPromosRepository.save(displayInTilesPromos);
        return modelMapper.map(updatedDisplayInTilesPromos,DisplayInTilesPromosDto.class);
    }

    @Override
    public byte[] downloadMobileImage(Long id) {
        DisplayInTilesPromos displayInTilesPromos=displayInTilesPromosRepository.findById(id).orElse(null);
        if(displayInTilesPromos!=null){
            return ImageUtils.decompressImage(displayInTilesPromos.getMobileImage());
        }
        return null;
    }

    @Override
    public byte[] downloadDesktopImage(Long id) {
        DisplayInTilesPromos displayInTilesPromos=displayInTilesPromosRepository.findById(id).orElse(null);
        if(displayInTilesPromos!=null){
            return ImageUtils.decompressImage(displayInTilesPromos.getDesktopImage());
        }
        return null;
    }

    @Override
    public String getMeasurement(Long id, String WhichImageMeasurements) {
        DisplayInTilesPromos displayInTilesPromos=displayInTilesPromosRepository.findById(id).orElse(null);
        if(displayInTilesPromos!=null){
            ImageMeasurementsDto imageMeasurementsDto=modelMapper.map(displayInTilesPromos,ImageMeasurementsDto.class);
            if(WhichImageMeasurements.equals("mobile")){
                return String.valueOf("("+imageMeasurementsDto.getMobilewidth()+"×"+imageMeasurementsDto.getMobileheight()+")");
            }
            else if(WhichImageMeasurements.equals("desktop")){
                return String.valueOf("("+imageMeasurementsDto.getDesktopwidth()+"×"+imageMeasurementsDto.getDesktopheight()+")");
            }
            else {
                return "There is only mobile and desktop measurements";
            }
        }
        else {
            return "No record found in the provided id";
        }
    }
    @Override
    public String deletePromos(Long id) {
        displayInTilesPromosRepository.deleteById(id);
        return "Promo deleted successfully";
    }

    @Override
    public String disablePromos(Long id) {
        DisplayInTilesPromos displayInTilesPromos=displayInTilesPromosRepository.findById(id).orElse(null);
        if(displayInTilesPromos!=null){
            displayInTilesPromos.setStatus(false);
            displayInTilesPromosRepository.save(displayInTilesPromos);
            return "Promo disabled successfully";
        }
        else {
            return "No record found in the provided id";
        }
    }

    @Override
    public String enablePromos(Long id) {
        DisplayInTilesPromos displayInTilesPromos=displayInTilesPromosRepository.findById(id).orElse(null);
        if(displayInTilesPromos!=null){
            displayInTilesPromos.setStatus(true);
            displayInTilesPromosRepository.save(displayInTilesPromos);
            return "Promo enabled successfully";
        }
        else {
            return "No record found in the provided id";
        }
    }

    @Override
    public DisplayInTilesPromosDto updateWholePromos(Long id, DisplayInTilesPromosDto displayInTilesPromosDto) {
        DisplayInTilesPromos displayInTilesPromos=displayInTilesPromosRepository.findById(id).orElse(null);
        if(displayInTilesPromos!=null){
            displayInTilesPromos.setDisplayPromoTitle(displayInTilesPromosDto.getDisplayPromoTitle());
            displayInTilesPromos.setStartingDate(displayInTilesPromosDto.getStartingDate());
            displayInTilesPromos.setEndingDate(displayInTilesPromosDto.getEndingDate());
            displayInTilesPromos.setLink(displayInTilesPromosDto.getLink());
            displayInTilesPromosRepository.save(displayInTilesPromos);
            return modelMapper.map(displayInTilesPromos,DisplayInTilesPromosDto.class);
        }

        else {
            return null;
        }
    }

    @Override
    public String uploadImages(Long id, byte[] mobileImage, byte[] desktopImage) throws IOException {
        DisplayInTilesPromos displayInTilesPromos=displayInTilesPromosRepository.findById(id).orElse(null);
        if(displayInTilesPromos==null){
            throw new RuntimeException("DisplayInTilesPromos not found");
        }
        else {
            displayInTilesPromos.setMobileImage(ImageUtils.compressImage(mobileImage));
            ImageDimensions dimensions = ImageDimensionExtractor.getImageDimensions(mobileImage);
            displayInTilesPromos.setMobilewidth(dimensions.getWidth());
            displayInTilesPromos.setMobileheight(dimensions.getHeight());
            displayInTilesPromos.setDesktopImage(ImageUtils.compressImage(desktopImage));
            ImageDimensions dimensions1 = ImageDimensionExtractor.getImageDimensions(desktopImage);
            displayInTilesPromos.setDesktopwidth(dimensions1.getWidth());
            displayInTilesPromos.setDesktopheight(dimensions1.getHeight());
            displayInTilesPromosRepository.save(displayInTilesPromos);
            return "Images uploaded successfully";
        }
    }
}
