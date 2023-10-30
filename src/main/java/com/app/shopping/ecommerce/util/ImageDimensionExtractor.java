package com.app.shopping.ecommerce.util;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ImageDimensionExtractor {
    public static ImageDimensions getImageDimensions(byte[] imageData) throws IOException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(imageData)) {
            BufferedImage image = ImageIO.read(bis);
            if (image != null) {
                int width = image.getWidth();
                int height = image.getHeight();
                return new ImageDimensions(width, height);
            } else {
                throw new IOException("Failed to read the image.");
            }
        }
    }
}
