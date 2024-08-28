package com.app.shopping.ecommerce.util;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Executable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class ImageUtilsTest {
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setErr(new PrintStream(errContent));
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void restoreStreams() {
        System.setErr(originalErr);
    }
@Test
public void testCompress() {
    byte[] data = "This is a test string to be compressed and decompressed".getBytes();
    byte[] compressedData = ImageUtils.compressImage(data);
    assertNotNull(compressedData);
    assertTrue(compressedData.length < data.length, "Compressed data should be smaller than the original data");
}

@Test
void testDecompressException() {
    byte[] invalidData = "This is a test string to be compressed and decompressed".getBytes();
    byte[] tmp=new byte[4*1024];
    ImageUtils.decompressImage(invalidData);
    assertEquals("java.util.zip.DataFormatException: inco", errContent.toString().substring(0, 39));

}
@Test
    void testDecompressImage(){
    byte[] data = "This is a test string to be compressed and decompressed".getBytes();

    // Compress the data
    byte[] compressedData = ImageUtils.compressImage(data);
    assertNotNull(compressedData);
    assertTrue(compressedData.length < data.length, "Compressed data should be smaller than the original data");
    // Decompress the data
    byte[] decompressedData = ImageUtils.decompressImage(compressedData);
    assertNotNull(decompressedData);
    assertArrayEquals(data, decompressedData, "Decompressed data should match the original data");
}
}