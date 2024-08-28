package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageMeasurementsDtoTest {
    ImageMeasurementsDto imageMeasurementsDto;
    @BeforeEach
    void setUp() {
        imageMeasurementsDto = new ImageMeasurementsDto();
        imageMeasurementsDto = new ImageMeasurementsDto(1, 2, 3, 4);
    }

    @Test
    void getMobilewidth() {
        assertEquals(1, imageMeasurementsDto.getMobilewidth());
    }

    @Test
    void getMobileheight() {
        assertEquals(2, imageMeasurementsDto.getMobileheight());
    }

    @Test
    void getDesktopwidth() {
        assertEquals(3, imageMeasurementsDto.getDesktopwidth());
    }

    @Test
    void getDesktopheight() {
        assertEquals(4, imageMeasurementsDto.getDesktopheight());
    }

    @Test
    void setMobilewidth() {
        imageMeasurementsDto.setMobilewidth(5);
        assertEquals(5, imageMeasurementsDto.getMobilewidth());
    }

    @Test
    void setMobileheight() {
        imageMeasurementsDto.setMobileheight(6);
        assertEquals(6, imageMeasurementsDto.getMobileheight());
    }

    @Test
    void setDesktopwidth() {
        imageMeasurementsDto.setDesktopwidth(7);
        assertEquals(7, imageMeasurementsDto.getDesktopwidth());
    }

    @Test
    void setDesktopheight() {
        imageMeasurementsDto.setDesktopheight(8);
        assertEquals(8, imageMeasurementsDto.getDesktopheight());
    }
}