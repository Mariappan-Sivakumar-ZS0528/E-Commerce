package com.app.shopping.ecommerce.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageDimensionExtractorTest {
    MockedStatic<ImageIO> mockedImageIO;
    byte[] imageData;
    BufferedImage image;

    @BeforeEach
    void setUp() throws IOException {
        imageData = new byte[0];
        mockedImageIO = Mockito.mockStatic(ImageIO.class);
        image= Mockito.mock(BufferedImage.class);
        Mockito.when(ImageIO.read(Mockito.any(ByteArrayInputStream.class))).thenReturn(image);
        Mockito.when(image.getWidth()).thenReturn(100);
        Mockito.when(image.getHeight()).thenReturn(200);


    }

    @AfterEach
    void tearDown() {
        mockedImageIO.close();
    }

    @Test
    void getImageDimensions() throws IOException {
        ImageDimensions imageDimensions= ImageDimensionExtractor.getImageDimensions(imageData);
        assertEquals(100, imageDimensions.getWidth());
        assertEquals(200, imageDimensions.getHeight());
    }

    @Test
    void getImageDimensions_ThrowIOException() throws IOException {
        Mockito.when(ImageIO.read(Mockito.any(ByteArrayInputStream.class))).thenReturn(null);
        assertThrows(IOException.class, () -> ImageDimensionExtractor.getImageDimensions(imageData));
    }
}