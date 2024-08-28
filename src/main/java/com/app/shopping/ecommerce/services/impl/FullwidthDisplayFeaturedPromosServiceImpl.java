package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.FullwidthDisplayFeaturedPromos;
import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;
import com.app.shopping.ecommerce.payload.ImageMeasurementsDto;
import com.app.shopping.ecommerce.repository.FullwidthDisplayFeaturedPromosRepository;
import com.app.shopping.ecommerce.services.FullwidthDisplayFeaturedPromosService;
import com.app.shopping.ecommerce.util.ImageDimensionExtractor;
import com.app.shopping.ecommerce.util.ImageDimensions;
import com.app.shopping.ecommerce.util.ImageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    @Override
    public Optional<byte[]> downloadMobileImage(Long id) {
        FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos=fullwidthDisplayFeaturedPromosRepository.findById(id).orElse(null);
        if(fullwidthDisplayFeaturedPromos!=null){
            return Optional.of(ImageUtils.decompressImage(fullwidthDisplayFeaturedPromos.getMobileImage()));
        }
        return Optional.empty();
    }
    @Override
    public Optional<byte[]> downloadDesktopImage(Long id) {
        FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos=fullwidthDisplayFeaturedPromosRepository.findById(id).orElse(null);
        if(fullwidthDisplayFeaturedPromos!=null){
            return Optional.of(ImageUtils.decompressImage(fullwidthDisplayFeaturedPromos.getDesktopImage()));
        }
        return Optional.empty();
    }
    @Override
    public String getMeasurement(Long id,String WhichImageMeasurements) {
        FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos=fullwidthDisplayFeaturedPromosRepository.findById(id).orElse(null);
        if(fullwidthDisplayFeaturedPromos!=null){
            ImageMeasurementsDto imageMeasurementsDto=modelMapper.map(fullwidthDisplayFeaturedPromos,ImageMeasurementsDto.class);
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
        fullwidthDisplayFeaturedPromosRepository.deleteById(id);
        return "Promo deleted successfully";
    }
    @Override
    public String disablePromos(Long id) {
        FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos=fullwidthDisplayFeaturedPromosRepository.findById(id).orElse(null);
        if(fullwidthDisplayFeaturedPromos!=null){
            fullwidthDisplayFeaturedPromos.setStatus(false);
            fullwidthDisplayFeaturedPromosRepository.save(fullwidthDisplayFeaturedPromos);
            return "Promo disabled successfully";
        }
        else {
            return "No record found in the provided id";
        }
    }
    @Override
    public String enablePromos(Long id) {
        FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos=fullwidthDisplayFeaturedPromosRepository.findById(id).orElse(null);
        if(fullwidthDisplayFeaturedPromos!=null){
            fullwidthDisplayFeaturedPromos.setStatus(true);
            fullwidthDisplayFeaturedPromosRepository.save(fullwidthDisplayFeaturedPromos);
            return "Promo enabled successfully";
        }
        else {
            return "No record found in the provided id";
        }
    }
    @Override
    public Optional<FullwidthDisplayFeaturedPromosDto> updateWholePromos(Long id, FullwidthDisplayFeaturedPromosDto fullwidthDisplayFeaturedPromosDto) {
        Optional<FullwidthDisplayFeaturedPromos> fullwidthDisplayFeaturedPromos=fullwidthDisplayFeaturedPromosRepository.findById(id);
        if(!fullwidthDisplayFeaturedPromos.isEmpty()){
            fullwidthDisplayFeaturedPromos.get().setPromoTitle(fullwidthDisplayFeaturedPromosDto.getPromoTitle());
            fullwidthDisplayFeaturedPromos.get().setStartingDate(fullwidthDisplayFeaturedPromosDto.getStartingDate());
            fullwidthDisplayFeaturedPromos.get().setEndingDate(fullwidthDisplayFeaturedPromosDto.getEndingDate());
            fullwidthDisplayFeaturedPromos.get().setLink(fullwidthDisplayFeaturedPromosDto.getLink());
            fullwidthDisplayFeaturedPromosRepository.save(fullwidthDisplayFeaturedPromos.get());
            return Optional.of(modelMapper.map(fullwidthDisplayFeaturedPromos.get(),FullwidthDisplayFeaturedPromosDto.class));
        }
        else {
            return Optional.empty();
        }
    }
    @Override
    public String uploadImages(Long id,byte[] mobileImage, byte[] desktopImage) throws IOException {
        FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos=fullwidthDisplayFeaturedPromosRepository.findById(id).orElse(null);
        if(fullwidthDisplayFeaturedPromos==null){
            return "No record found in the provided id";
        }
        else {
            fullwidthDisplayFeaturedPromos.setMobileImage(ImageUtils.compressImage(mobileImage));
            ImageDimensions dimensions = ImageDimensionExtractor.getImageDimensions(mobileImage);
            fullwidthDisplayFeaturedPromos.setMobilewidth(dimensions.getWidth());
            fullwidthDisplayFeaturedPromos.setMobileheight(dimensions.getHeight());
            fullwidthDisplayFeaturedPromos.setDesktopImage(ImageUtils.compressImage(desktopImage));
            ImageDimensions dimensions1 = ImageDimensionExtractor.getImageDimensions(desktopImage);
            fullwidthDisplayFeaturedPromos.setDesktopwidth(dimensions1.getWidth());
            fullwidthDisplayFeaturedPromos.setDesktopheight(dimensions1.getHeight());
            fullwidthDisplayFeaturedPromosRepository.save(fullwidthDisplayFeaturedPromos);
            return "Images uploaded successfully";
        }
    }
}