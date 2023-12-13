package com.app.shopping.ecommerce.services.impl;
import com.app.shopping.ecommerce.entity.FullwidthDisplayFeaturedPromos;
import com.app.shopping.ecommerce.payload.FullwidthDisplayFeaturedPromosDto;
import com.app.shopping.ecommerce.payload.ImageMeasurementsDto;
import com.app.shopping.ecommerce.repository.FullwidthDisplayFeaturedPromosRepository;
import com.app.shopping.ecommerce.services.FullwidthDisplayFeaturedPromosService;
import com.app.shopping.ecommerce.services.impl.FullwidthDisplayFeaturedPromosServiceImpl;
import com.app.shopping.ecommerce.util.ImageUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FullwidthDisplayFeaturedPromosServiceImplTest {
    @Mock
    private FullwidthDisplayFeaturedPromosRepository fullwidthDisplayFeaturedPromosRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private FullwidthDisplayFeaturedPromosServiceImpl fullwidthDisplayFeaturedPromosService;

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
    void getMeasurement() {
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
        String str1=fullwidthDisplayFeaturedPromosService.getMeasurement(1L,"mobile");
        assertThat(str1,is("(101×101)"));
    }

    @Test
    void disablePromos() {
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
    void enablePromos() {
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
    void updateWholePromos() {
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
        FullwidthDisplayFeaturedPromosDto finalDto=fullwidthDisplayFeaturedPromosService.updateWholePromos(1L,dto);
        assertThat(finalDto.getPromoTitle(),is("test1"));
        assertThat(finalDto.getId(),is(1L));
    }
    @Test
    void deletePromos() {
        assertThat(fullwidthDisplayFeaturedPromosService.deletePromos(1L),is("Promo deleted successfully"));
    }
    @Test
    void uploadImages() {
    }
}