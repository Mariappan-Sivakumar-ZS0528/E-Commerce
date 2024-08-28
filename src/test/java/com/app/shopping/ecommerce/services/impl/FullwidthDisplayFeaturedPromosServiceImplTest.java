package com.app.shopping.ecommerce.services.impl;
import com.app.shopping.ecommerce.entity.FullwidthDisplayFeaturedPromos;
import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;
import com.app.shopping.ecommerce.payload.ImageMeasurementsDto;
import com.app.shopping.ecommerce.repository.FullwidthDisplayFeaturedPromosRepository;
import com.app.shopping.ecommerce.services.FullwidthDisplayFeaturedPromosService;
import com.app.shopping.ecommerce.services.impl.FullwidthDisplayFeaturedPromosServiceImpl;
import com.app.shopping.ecommerce.util.ImageDimensionExtractor;
import com.app.shopping.ecommerce.util.ImageDimensions;
import com.app.shopping.ecommerce.util.ImageUtils;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.PrepareTestInstance;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
//@Prepare({ImageUtils.class, ImageDimensionExtractor.class})
public class FullwidthDisplayFeaturedPromosServiceImplTest {
    @Mock
    private FullwidthDisplayFeaturedPromosRepository fullwidthDisplayFeaturedPromosRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private FullwidthDisplayFeaturedPromosServiceImpl fullwidthDisplayFeaturedPromosService;
    @Mock
    ImageDimensions mobileDimensions = new ImageDimensions(100, 200);
    @Mock
    ImageDimensions desktopDimensions = new ImageDimensions(300, 400);
    private static MockedStatic<ImageUtils> imageUtilsMockedStatic;
    private static MockedStatic<ImageDimensionExtractor> imageDimensionExtractorMockedStatic;
    @BeforeAll
    public static void setup() {
        imageUtilsMockedStatic= mockStatic(ImageUtils.class);
        imageDimensionExtractorMockedStatic = mockStatic(ImageDimensionExtractor.class);
    }
    @AfterAll
    public static void teardown() {
        imageUtilsMockedStatic.close();
        imageDimensionExtractorMockedStatic.close();
    }

    //    @BeforeEach
//    void setupService() {
//        modelMapper=mock(ModelMapper.class);
//        fullwidthDisplayFeaturedPromosRepository = mock(FullwidthDisplayFeaturedPromosRepository.class);
//        fullwidthDisplayFeaturedPromosService = new FullwidthDisplayFeaturedPromosServiceImpl(fullwidthDisplayFeaturedPromosRepository,modelMapper);
//    }
    @Test
    public void testgetall(){
        FullwidthDisplayFeaturedPromos entity = new FullwidthDisplayFeaturedPromos();
        entity.setId(1L);
        entity.setPromoTitle("test");
        FullwidthDisplayFeaturedPromos entity1 = new FullwidthDisplayFeaturedPromos();
        entity1.setId(1L);
        entity1.setPromoTitle("test");
        when(fullwidthDisplayFeaturedPromosRepository.findAll()).thenReturn(List.of(entity,entity1));
        FullwidthDisplayFeaturedPromosDto dto1 = new FullwidthDisplayFeaturedPromosDto();
        FullwidthDisplayFeaturedPromosDto dto2 = new FullwidthDisplayFeaturedPromosDto();
        dto1.setId(1L);
        dto1.setPromoTitle("test");
        dto2.setId(1L);
        dto2.setPromoTitle("test");
        when(modelMapper.map(entity,FullwidthDisplayFeaturedPromosDto.class)).thenReturn(dto1);
        when(modelMapper.map(entity1,FullwidthDisplayFeaturedPromosDto.class)).thenReturn(dto2);
        List<FullwidthDisplayFeaturedPromosDto> dtofinal=fullwidthDisplayFeaturedPromosService.getall();
        assertThat(dtofinal.size(),is(2));
        assertThat(dtofinal.get(0).getPromoTitle(),is("test"));
        assertThat(dtofinal.size()==List.of(entity,entity1).size(),is(true));
    }

    @Test
    void add() {
       FullwidthDisplayFeaturedPromos entity = new FullwidthDisplayFeaturedPromos();
       entity.setId(1L);
       entity.setPromoTitle("test");
       FullwidthDisplayFeaturedPromosDto fdto = new FullwidthDisplayFeaturedPromosDto();
       fdto.setId(1L);
       fdto.setPromoTitle("test");
       when(modelMapper.map(fdto,FullwidthDisplayFeaturedPromos.class)).thenReturn(entity);
       when(modelMapper.map(entity,FullwidthDisplayFeaturedPromosDto.class)).thenReturn(fdto);
       when(fullwidthDisplayFeaturedPromosRepository.save(entity)).thenReturn(entity);
       FullwidthDisplayFeaturedPromosDto finalDto = fullwidthDisplayFeaturedPromosService.add(fdto);
       assertThat(finalDto.getPromoTitle(),is("test"));
       assertThat(finalDto.getId(),is(1L));
    }
    @Test
    void getMeasurementForMobile() {
        FullwidthDisplayFeaturedPromos entity=new FullwidthDisplayFeaturedPromos();
        entity.setId(1L);
        entity.setPromoTitle("test");
        entity.setDesktopheight(100);
        entity.setDesktopwidth(100);
        entity.setMobileheight(101);
        entity.setMobilewidth(101);
        ImageMeasurementsDto imageMeasurementsDto=new ImageMeasurementsDto(101,101,100,100);
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity,ImageMeasurementsDto.class)).thenReturn(imageMeasurementsDto);
        String str1=fullwidthDisplayFeaturedPromosService.getMeasurement(1L,"mobile");
        assertThat(str1,is("(101×101)"));
    }
    @Test
    void getMeasurementForFailure() {
        FullwidthDisplayFeaturedPromos entity=new FullwidthDisplayFeaturedPromos();
        entity.setId(1L);
        entity.setPromoTitle("test");
        entity.setDesktopheight(100);
        entity.setDesktopwidth(100);
        entity.setMobileheight(101);
        entity.setMobilewidth(101);
        ImageMeasurementsDto imageMeasurementsDto=new ImageMeasurementsDto(101,101,100,100);
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity,ImageMeasurementsDto.class)).thenReturn(imageMeasurementsDto);
        String str2= fullwidthDisplayFeaturedPromosService.getMeasurement(1L, "sdds");
        assertThat(str2,is("There is only mobile and desktop measurements"));
    }
    @Test
    void getMeasurementForNullData(){
        FullwidthDisplayFeaturedPromos entity=new FullwidthDisplayFeaturedPromos();
        entity.setId(1L);
        entity.setPromoTitle("test");
        entity.setDesktopheight(100);
        entity.setDesktopwidth(100);
        entity.setMobileheight(101);
        entity.setMobilewidth(101);
        ImageMeasurementsDto imageMeasurementsDto=new ImageMeasurementsDto(101,101,100,100);
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity,ImageMeasurementsDto.class)).thenReturn(imageMeasurementsDto);
        String str3= fullwidthDisplayFeaturedPromosService.getMeasurement(2L, "sdds");
        assertThat(str3,is("No record found in the provided id"));
    }
    @Test
    void getMeasurementForDesktop() {
        FullwidthDisplayFeaturedPromos entity=new FullwidthDisplayFeaturedPromos();
        entity.setId(1L);
        entity.setPromoTitle("test");
        entity.setDesktopheight(100);
        entity.setDesktopwidth(100);
        entity.setMobileheight(101);
        entity.setMobilewidth(101);
        ImageMeasurementsDto imageMeasurementsDto=new ImageMeasurementsDto(101,101,100,100);
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity,ImageMeasurementsDto.class)).thenReturn(imageMeasurementsDto);
        String str=fullwidthDisplayFeaturedPromosService.getMeasurement(1L,"desktop");
        assertThat(str,is("(100×100)"));
    }

    @Test
    void disablePromos_Success() {
        FullwidthDisplayFeaturedPromos entity=new FullwidthDisplayFeaturedPromos();
        entity.setId(1L);
        entity.setPromoTitle("test");
        entity.setStatus(true);
        FullwidthDisplayFeaturedPromos entity1=new FullwidthDisplayFeaturedPromos();
        entity.setId(1L);
        entity.setPromoTitle("test");
        entity.setStatus(false);
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(fullwidthDisplayFeaturedPromosRepository.save(entity)).thenReturn(entity1);
        String str=fullwidthDisplayFeaturedPromosService.disablePromos(1L);
        assertThat(str,is("Promo disabled successfully"));
    }

    @Test
    void disablePromos_Failure(){
        FullwidthDisplayFeaturedPromos entity=new FullwidthDisplayFeaturedPromos();
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.empty());
        String str=fullwidthDisplayFeaturedPromosService.disablePromos(1L);
        assertThat(str,is("No record found in the provided id"));
    }
    @Test
    void enablePromos_Sucess() {
        FullwidthDisplayFeaturedPromos entity=new FullwidthDisplayFeaturedPromos();
        entity.setId(1L);
        entity.setPromoTitle("test");
        entity.setStatus(true);
        FullwidthDisplayFeaturedPromos entity1=new FullwidthDisplayFeaturedPromos();
        entity.setId(1L);
        entity.setPromoTitle("test");
        entity.setStatus(false);
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(fullwidthDisplayFeaturedPromosRepository.save(entity)).thenReturn(entity1);
        String str=fullwidthDisplayFeaturedPromosService.enablePromos(1L);
        assertThat(str,is("Promo enabled successfully"));
    }
    @Test
    void enablePromos_Failure() {
        FullwidthDisplayFeaturedPromos entity=new FullwidthDisplayFeaturedPromos();
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.empty());
        String str=fullwidthDisplayFeaturedPromosService.enablePromos(1L);
        assertThat(str,is("No record found in the provided id"));
    }

    @Test
    void updateWholePromos_Sucess() {
        FullwidthDisplayFeaturedPromos entity=new FullwidthDisplayFeaturedPromos();
        entity.setId(1L);
        entity.setPromoTitle("test");
        FullwidthDisplayFeaturedPromosDto dto=new FullwidthDisplayFeaturedPromosDto();
        dto.setId(1L);
        dto.setPromoTitle("test");
        FullwidthDisplayFeaturedPromosDto dto2=new FullwidthDisplayFeaturedPromosDto();
        dto2.setId(1L);
        dto2.setPromoTitle("test1");
        when(modelMapper.map(dto,FullwidthDisplayFeaturedPromos.class)).thenReturn(entity);
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity,FullwidthDisplayFeaturedPromosDto.class)).thenReturn(dto2);
        Optional<FullwidthDisplayFeaturedPromosDto> finalDto=fullwidthDisplayFeaturedPromosService.updateWholePromos(1L,dto);
        assertThat(finalDto.get().getPromoTitle(),is("test1"));
        assertThat(finalDto.get().getId(),is(1L));
    }
    @Test
    void updateWholePromos_Failure() {
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<FullwidthDisplayFeaturedPromosDto> fullwidthDisplayFeaturedPromosDto=fullwidthDisplayFeaturedPromosService.updateWholePromos(1L,new FullwidthDisplayFeaturedPromosDto());
        assertThat(fullwidthDisplayFeaturedPromosDto,is(Optional.empty()));
    }
    @Test
    void deletePromos() {
        assertThat(fullwidthDisplayFeaturedPromosService.deletePromos(1L),is("Promo deleted successfully"));
    }
    @Test
    void uploadImagesForSucess() throws IOException {
        FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos = new FullwidthDisplayFeaturedPromos();
        byte[] mobileImage = new byte[]{1, 2, 3, 4, 5};
        byte[] desktopImage = new byte[]{6, 7, 8, 9, 10};
        fullwidthDisplayFeaturedPromos.setId(1L);
        fullwidthDisplayFeaturedPromos.setDesktopImage(desktopImage);
        fullwidthDisplayFeaturedPromos.setMobileImage(mobileImage);
        when(mobileDimensions.getHeight()).thenReturn(200);
        when(mobileDimensions.getWidth()).thenReturn(100);
        when(desktopDimensions.getHeight()).thenReturn(400);
        when(desktopDimensions.getWidth()).thenReturn(300);
        when(ImageDimensionExtractor.getImageDimensions(desktopImage)).thenReturn(desktopDimensions);
        when(ImageDimensionExtractor.getImageDimensions(mobileImage)).thenReturn(mobileDimensions);
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.of(fullwidthDisplayFeaturedPromos));
        when(fullwidthDisplayFeaturedPromosRepository.save(fullwidthDisplayFeaturedPromos)).thenReturn(fullwidthDisplayFeaturedPromos);
        String str=fullwidthDisplayFeaturedPromosService.uploadImages(1L,mobileImage,desktopImage);
        assertThat(str,is("Images uploaded successfully"));
    }

    @Test
    void uploadImagesForFailure() throws IOException {
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.empty());
        String str=fullwidthDisplayFeaturedPromosService.uploadImages(1L,new byte[]{1, 2, 3, 4, 5},new byte[]{6, 7, 8, 9, 10});
        assertThat(str,is("No record found in the provided id"));
    }

    @Test
    void downloadMobileImageForSuccess() {
        FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos = new FullwidthDisplayFeaturedPromos();
        byte[] mock=new byte[]{1, 2, 3, 4, 5};
        fullwidthDisplayFeaturedPromos.setId(1L);
        fullwidthDisplayFeaturedPromos.setMobileImage(mock);
//        mockStatic(ImageUtils.class);
//        mockStatic(ImageDimensionExtractor.class);
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.of(fullwidthDisplayFeaturedPromos));
        when(ImageUtils.decompressImage(fullwidthDisplayFeaturedPromos.getMobileImage())).thenReturn(mock);
        Optional<byte[]> str=fullwidthDisplayFeaturedPromosService.downloadMobileImage(1L);
        assertThat(str.get(),is(mock));
    }
    @Test
    void downloadMobileImageForFailure() {
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<byte[]> str=fullwidthDisplayFeaturedPromosService.downloadMobileImage(1L);
        assertThat(str,is(Optional.empty()));
    }

    @Test
    void downloadDesktopImageForSuccess() {
        FullwidthDisplayFeaturedPromos fullwidthDisplayFeaturedPromos = new FullwidthDisplayFeaturedPromos();
        byte[] mock=new byte[]{1, 2, 3, 4, 5};
        fullwidthDisplayFeaturedPromos.setId(1L);
        fullwidthDisplayFeaturedPromos.setMobileImage(mock);
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.of(fullwidthDisplayFeaturedPromos));
        when(ImageUtils.decompressImage(fullwidthDisplayFeaturedPromos.getDesktopImage())).thenReturn(mock);
        Optional<byte[]> str=fullwidthDisplayFeaturedPromosService.downloadDesktopImage(1L);
        assertThat(str.get(),is(mock));
    }
    @Test
    void downloadDesktopImageForFailure(){
        when(fullwidthDisplayFeaturedPromosRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<byte[]> str=fullwidthDisplayFeaturedPromosService.downloadDesktopImage(1L);
        assertThat(str,is(Optional.empty()));
    }
}