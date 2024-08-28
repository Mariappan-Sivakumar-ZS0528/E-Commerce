package com.app.shopping.ecommerce.payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDtoTest {
    ProductDto productDto;
    @BeforeEach
    void setUp() {
        productDto = new ProductDto(1L,"name","description",10.0,10,10,10,"image1","image2","image3","image4","subCategoryName","categoryName");
    }

    @Test
    void getId() {
        assertEquals(1L,productDto.getId());
    }

    @Test
    void getName() {
        assertEquals("name",productDto.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description",productDto.getDescription());
    }

    @Test
    void getPrice() {
        assertEquals(10.0,productDto.getPrice());
    }

    @Test
    void getInventory() {
        assertEquals(10,productDto.getInventory());
    }

    @Test
    void getQuantity() {
        assertEquals(10,productDto.getQuantity());
    }

    @Test
    void getDiscount() {
        assertEquals(10,productDto.getDiscount());
    }

    @Test
    void getImageName1() {
        assertEquals("image1",productDto.getImageName1());
    }

    @Test
    void getImageName2() {
        assertEquals("image2",productDto.getImageName2());
    }

    @Test
    void getImageName3() {
        assertEquals("image3",productDto.getImageName3());
    }

    @Test
    void getImageName4() {
        assertEquals("image4",productDto.getImageName4());
    }

    @Test
    void getSubCategoryName() {
        assertEquals("subCategoryName",productDto.getSubCategoryName());
    }

    @Test
    void getCategoryName() {
        assertEquals("categoryName",productDto.getCategoryName());
    }

    @Test
    void setId() {
        productDto.setId(2L);
        assertEquals(2L,productDto.getId());
    }

    @Test
    void setName() {
        productDto.setName("name");
        assertEquals("name",productDto.getName());
    }

    @Test
    void setDescription() {
        productDto.setDescription("description");
        assertEquals("description",productDto.getDescription());
    }

    @Test
    void setPrice() {
        productDto.setPrice(2.0);
        assertEquals(2.0,productDto.getPrice());
    }

    @Test
    void setInventory() {
        productDto.setInventory(2);
        assertEquals(2,productDto.getInventory());
    }

    @Test
    void setQuantity() {
        productDto.setQuantity(2);
        assertEquals(2,productDto.getQuantity());
    }

    @Test
    void setDiscount() {
        productDto.setDiscount(2);
        assertEquals(2,productDto.getDiscount());
    }

    @Test
    void setImageName1() {
        productDto.setImageName1("image1");
        assertEquals("image1",productDto.getImageName1());
    }

    @Test
    void setImageName2() {
        productDto.setImageName2("image2");
        assertEquals("image2",productDto.getImageName2());
    }

    @Test
    void setImageName3() {
        productDto.setImageName3("image3");
        assertEquals("image3",productDto.getImageName3());
    }

    @Test
    void setImageName4() {
        productDto.setImageName4("image4");
        assertEquals("image4",productDto.getImageName4());
    }

    @Test
    void setSubCategoryName() {
        productDto.setSubCategoryName("subCategoryName");
        assertEquals("subCategoryName",productDto.getSubCategoryName());
    }

    @Test
    void setCategoryName() {
        productDto.setCategoryName("categoryName");
        assertEquals("categoryName",productDto.getCategoryName());
    }
}