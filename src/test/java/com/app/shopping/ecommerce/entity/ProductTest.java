package com.app.shopping.ecommerce.entity;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;
    @BeforeEach
    void setUp() throws ParseException {
        byte[] bytes = new byte[]{1,2,3};
        product = new Product(1L,"product","description",1.0,1,1,"image1","image2","image3","image4",bytes,bytes,bytes,bytes,"type1","type2","type3", "type4",true,"2",new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-01"),new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-31"),2.4,new Category(),new SubCategory(),new Supplier());
        product.setStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-01"));
        product.setEndingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-31"));
    }

    @Test
    void isActive() throws ParseException {
        assertTrue(product.isActive());
        product.setEndingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-31"));
        assertEquals(false,product.isActive());
    }

    @Test
    void getId() {
        product.setId(1L);
        assertEquals(1L,product.getId());
    }

    @Test
    void getName() {
        product.setName("name");
        assertEquals("name",product.getName());
    }

    @Test
    void getDescription() {
        product.setDescription("description");
        assertEquals("description",product.getDescription());
    }

    @Test
    void getPrice() {
        product.setPrice(1.0);
        assertEquals(1.0,product.getPrice());
    }

    @Test
    void getInventory() {
        product.setInventory(1);
        assertEquals(1,product.getInventory());
    }

    @Test
    void getQuantity() {
        product.setQuantity(1);
        assertEquals(1,product.getQuantity());
    }

    @Test
    void getImageName1() {
        product.setImageName1("image1");
        assertEquals("image1",product.getImageName1());
    }

    @Test
    void getImageName2() {
        product.setImageName2("image2");
        assertEquals("image2",product.getImageName2());
    }

    @Test
    void getImageName3() {
        product.setImageName3("image3");
        assertEquals("image3",product.getImageName3());
    }

    @Test
    void getImageName4() {
        product.setImageName4("image4");
        assertEquals("image4",product.getImageName4());
    }

    @Test
    void getImageData1() {
        byte[] bytes = new byte[]{1,2,3};
        product.setImageData1(bytes);
        assertEquals(bytes,product.getImageData1());
    }

    @Test
    void getImageData2() {
        byte[] bytes = new byte[]{1,2,3};
        product.setImageData2(bytes);
        assertEquals(bytes,product.getImageData2());
    }

    @Test
    void getImageData3() {
        byte[] bytes = new byte[]{1,2,3};
        product.setImageData3(bytes);
        assertEquals(bytes,product.getImageData3());
    }

    @Test
    void getImageData4() {
        byte[] bytes = new byte[]{1,2,3};
        product.setImageData4(bytes);
        assertEquals(bytes,product.getImageData4());
    }

    @Test
    void getImageType1() {
        product.setImageType1("imageType1");
        assertEquals("imageType1",product.getImageType1());
    }

    @Test
    void getImageType2() {
        product.setImageType2("imageType2");
        assertEquals("imageType2",product.getImageType2());
    }

    @Test
    void getImageType3() {
        product.setImageType3("imageType3");
        assertEquals("imageType3",product.getImageType3());
    }

    @Test
    void getImageType4() {
        product.setImageType4("imageType4");
        assertEquals("imageType4",product.getImageType4());
    }

    @Test
    void getOfferDurationType() {
        product.setOfferDurationType("offerDurationType");
        assertEquals("offerDurationType",product.getOfferDurationType());
    }

    @Test
    void getStartingDate() {
        Date date = new Date();
        product.setStartingDate(date);
        assertEquals(date,product.getStartingDate());
    }

    @Test
    void getEndingDate() {
        Date date = new Date();
        product.setEndingDate(date);
        assertEquals(date,product.getEndingDate());
    }

    @Test
    void getDiscount() {
        product.setDiscount(1.0);
        assertEquals(1.0,product.getDiscount());
    }

    @Test
    void getCategory() {
        Category category = new Category();
        product.setCategory(category);
        assertEquals(category,product.getCategory());
    }

    @Test
    void getSubCategory() {
        SubCategory subCategory = new SubCategory();
        product.setSubCategory(subCategory);
        assertEquals(subCategory,product.getSubCategory());
    }

    @Test
    void getSupplier() {
        Supplier supplier = new Supplier();
        product.setSupplier(supplier);
        assertEquals(supplier,product.getSupplier());
    }

    @Test
    void setId() {
        product.setId(1L);
        assertEquals(1L,product.getId());
    }

    @Test
    void setName() {
        product.setName("name");
        assertEquals("name",product.getName());
    }

    @Test
    void setDescription() {
        product.setDescription("description");
        assertEquals("description",product.getDescription());
    }

    @Test
    void setPrice() {
        product.setPrice(1.0);
        assertEquals(1.0,product.getPrice());
    }

    @Test
    void setInventory() {
        product.setInventory(1);
        assertEquals(1,product.getInventory());
    }

    @Test
    void setQuantity() {
        product.setQuantity(1);
        assertEquals(1,product.getQuantity());
    }

    @Test
    void setImageName1() {
        product.setImageName1("image1");
        assertEquals("image1",product.getImageName1());
    }

    @Test
    void setImageName2() {
        product.setImageName2("image2");
        assertEquals("image2",product.getImageName2());
    }

    @Test
    void setImageName3() {
        product.setImageName3("image3");
        assertEquals("image3",product.getImageName3());
    }

    @Test
    void setImageName4() {
        product.setImageName4("image4");
        assertEquals("image4",product.getImageName4());
    }

    @Test
    void setImageData1() {
        byte[] bytes = new byte[]{1,2,3};
        product.setImageData1(bytes);
        assertEquals(bytes,product.getImageData1());
    }

    @Test
    void setImageData2() {
        byte[] bytes = new byte[]{1,2,3};
        product.setImageData2(bytes);
        assertEquals(bytes,product.getImageData2());
    }

    @Test
    void setImageData3() {
        byte[] bytes = new byte[]{1,2,3};
        product.setImageData3(bytes);
        assertEquals(bytes,product.getImageData3());
    }

    @Test
    void setImageData4() {
        byte[] bytes = new byte[]{1,2,3};
        product.setImageData4(bytes);
        assertEquals(bytes,product.getImageData4());
    }

    @Test
    void setImageType1() {
        product.setImageType1("imageType1");
        assertEquals("imageType1",product.getImageType1());
    }

    @Test
    void setImageType2() {
        product.setImageType2("imageType2");
        assertEquals("imageType2",product.getImageType2());
    }

    @Test
    void setImageType3() {
        product.setImageType3("imageType3");
        assertEquals("imageType3",product.getImageType3());
    }

    @Test
    void setImageType4() {
        product.setImageType4("imageType4");
        assertEquals("imageType4",product.getImageType4());
    }

    @Test
    void setActive() {
        product.setActive(true);
        assertEquals(true,product.isActive());
    }

    @Test
    void setOfferDurationType() {
        product.setOfferDurationType("offerDurationType");
        assertEquals("offerDurationType",product.getOfferDurationType());
    }

    @Test
    void setStartingDate() {
        Date date = new Date();
        product.setStartingDate(date);
        assertEquals(date,product.getStartingDate());
    }

    @Test
    void setEndingDate() {
        Date date = new Date();
        product.setEndingDate(date);
        assertEquals(date,product.getEndingDate());
    }

    @Test
    void setDiscount() {
        product.setDiscount(1.0);
        assertEquals(1.0,product.getDiscount());
    }

    @Test
    void setCategory() {
        Category category = new Category();
        product.setCategory(category);
        assertEquals(category,product.getCategory());
    }

    @Test
    void setSubCategory() {
        SubCategory subCategory = new SubCategory();
        product.setSubCategory(subCategory);
        assertEquals(subCategory,product.getSubCategory());
    }

    @Test
    void setSupplier() {
        Supplier supplier = new Supplier();
        product.setSupplier(supplier);
        assertEquals(supplier,product.getSupplier());
    }
}