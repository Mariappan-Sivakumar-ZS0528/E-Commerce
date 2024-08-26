package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.CategoryDto;
import com.app.shopping.ecommerce.repository.CategoryRepository;
import com.app.shopping.ecommerce.services.CategoryService;
import com.app.shopping.ecommerce.util.ImageUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;
    @MockBean
    CategoryRepository categoryRepository;

    Category category;
    MultipartFile multipartFile;
    static byte[] bytes=new byte[1];
    private static MockedStatic<ImageUtils> mockedStaticImageUtils;

    @BeforeAll
    static void beforeAll() {
        if (mockedStaticImageUtils == null) {
            mockedStaticImageUtils = Mockito.mockStatic(ImageUtils.class);
            Mockito.when(ImageUtils.compressImage(Mockito.any(byte[].class))).thenReturn(bytes);
            Mockito.when(ImageUtils.decompressImage(Mockito.any(byte[].class))).thenReturn(bytes);
        }
    }

    @AfterAll
    static void afterAll() {
        mockedStaticImageUtils.close();
    }

    @BeforeEach
    void setUp() throws IOException {
        category=new Category();
        category.setId(1L);
        category.setName("Diary");
        category.setDescription("Diary Description");
        category.setDesktopImageName("desktop.png");
        category.setMobileImageName("mobile.png");
        category.setThumbnailImageName("thumbnail.png");
        category.setMobileImageData(bytes);
        category.setDesktopImageData(bytes);
        category.setThumbnailImageData(bytes);
        multipartFile = Mockito.mock(MultipartFile.class);
        Mockito.when(multipartFile.getOriginalFilename()).thenReturn("desktop.png");
        Mockito.when(multipartFile.getContentType()).thenReturn("image/png");
        Mockito.when(multipartFile.getBytes()).thenReturn(bytes);
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(category));
    }

    @Test
    void addCategory() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setName("Diary");
        categoryDto.setDescription("Diary Description");
        categoryDto.setDesktopImageName("desktop.png");
        categoryDto.setMobileImageName("mobile.png");
        categoryDto.setThumbnailImageName("thumbnail.png");
        CategoryDto categoryDto1=categoryService.addCategory(categoryDto);
        assertEquals("Diary", categoryDto1.getName());
        assertEquals("Diary Description", categoryDto1.getDescription());
        assertEquals("desktop.png", categoryDto1.getDesktopImageName());
        assertEquals("mobile.png", categoryDto1.getMobileImageName());
        assertEquals("thumbnail.png", categoryDto1.getThumbnailImageName());
    }

    @Test
    void updateCategory() {
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setName("Chicken");
        categoryDto.setDescription("Chicken Description");
        CategoryDto categoryDto1=categoryService.updateCategory(categoryDto, 1L);
        assertEquals("Chicken", categoryDto1.getName());
        assertEquals("Chicken Description", categoryDto1.getDescription());
    }
    @Test
    void updateCategoryThrowError() {
        assertThrows(ResourceNotFoundException.class,()->categoryService.updateCategory(new CategoryDto(), 5L));
    }

    @Test
    void getCategory() {
        CategoryDto categoryDto=categoryService.getCategory(1L);
        assertEquals("Diary", categoryDto.getName());
        assertEquals("Diary Description", categoryDto.getDescription());
        assertEquals("desktop.png", categoryDto.getDesktopImageName());
        assertEquals("mobile.png", categoryDto.getMobileImageName());
        assertEquals("thumbnail.png", categoryDto.getThumbnailImageName());
    }
    @Test
    void getCategoryThrowError() {
        assertThrows(ResourceNotFoundException.class,()->categoryService.getCategory(5L));
    }

    @Test
    void getAllCategories() {
        List<CategoryDto> categoryDtos=categoryService.getAllCategories();
        assertEquals("Diary", categoryDtos.get(0).getName());
        assertEquals("Diary Description", categoryDtos.get(0).getDescription());
        assertEquals("desktop.png", categoryDtos.get(0).getDesktopImageName());
        assertEquals("mobile.png", categoryDtos.get(0).getMobileImageName());
        assertEquals("thumbnail.png", categoryDtos.get(0).getThumbnailImageName());
    }

    @Test
    void deleteCategory() {
        assertDoesNotThrow(() -> categoryService.deleteCategory(1L));
    }
    @Test
    void deleteCategoryThrowError() {
        assertThrows(ResourceNotFoundException.class,()->categoryService.deleteCategory(5L));
    }

    @Test
    void updateCategoryImage() throws IOException {
        assertEquals("Category image updated successfully", categoryService.updateCategoryImage(multipartFile,multipartFile,multipartFile, 1L));
    }

    @Test
    void updateCategoryImageThrowError() throws IOException {
        assertThrows(ResourceNotFoundException.class,()->categoryService.updateCategoryImage(multipartFile,multipartFile,multipartFile, 5L));
    }

    @Test
    void downloadMobileImage() {
        assertEquals(bytes, categoryService.downloadMobileImage(1L,"mobile.png"));
    }

    @Test
    void downloadMobileImageThrowResourceNotFoundError() {
        assertThrows(ResourceNotFoundException.class,()->categoryService.downloadMobileImage(5L,"mobile.png"));
    }

    @Test
    void downloadMobileImageNameNotMatch() {
        assertEquals(null, categoryService.downloadMobileImage(1L,"desktop.png"));
    }

    @Test
    void downloadDesktopImage() {
        assertEquals(bytes, categoryService.downloadDesktopImage(1L,"desktop.png"));
    }

    @Test
    void downloadDesktopImageThrowResourceNotFoundError() {
        assertThrows(ResourceNotFoundException.class,()->categoryService.downloadDesktopImage(5L,"desktop.png"));
    }

    @Test
    void downloadDesktopImageNameNotMatch() {
        assertEquals(null, categoryService.downloadDesktopImage(1L,"mobile.png"));
    }

    @Test
    void downloadThumbnailImage() {
        assertEquals(bytes, categoryService.downloadThumbnailImage(1L,"thumbnail.png"));
    }

    @Test
    void downloadThumbnailImageThrowResourceNotFoundError() {
        assertThrows(ResourceNotFoundException.class,()->categoryService.downloadThumbnailImage(5L,"thumbnail.png"));
    }

    @Test
    void downloadThumbnailImageNameNotMatch() {
        assertEquals(null, categoryService.downloadThumbnailImage(1L,"desktop.png"));
    }

}