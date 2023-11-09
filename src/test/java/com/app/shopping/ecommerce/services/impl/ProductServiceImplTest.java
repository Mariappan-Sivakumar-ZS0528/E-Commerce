package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.entity.Product;
import com.app.shopping.ecommerce.entity.SubCategory;
import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.repository.CategoryRepository;
import com.app.shopping.ecommerce.repository.ProductRepository;
import com.app.shopping.ecommerce.repository.SubCategoryRepository;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    CategoryRepository categoryRepository;
    @MockBean
    SubCategoryRepository subCategoryRepository;
    @MockBean
    SupplierRepository supplierRepository;
    Product product;
    Supplier supplier;
    Category category;
    SubCategory subCategory;
    MockHttpServletRequest request = new MockHttpServletRequest();

    @BeforeEach
    void setUp() {
        product=new Product();
        product.setId(1L);
        product.setName("Cow Milk");
        product.setPrice(100);
        product.setInventory(10);
        product.setQuantity(5);
        product.setDescription("Product Description");

        supplier=new Supplier();
        supplier.setId(1L);
        supplier.setCompany("ABC");
        supplier.setSupplierName("supplierName");
        supplier.setAddressLine1("123");
        supplier.setAddressLine2("Abc testing");
        supplier.setCity("Tuticorin");
        supplier.setContactNumber("1234567890");
        supplier.setEmail("abc@gmail.com");
        supplier.setProfitSharingPercentage(10);
        supplier.setProducts(Set.of(product));

        category=new Category();
        category.setId(1L);
        category.setName("Diary");
        category.setDescription("Diary Description");
        category.setDesktopImageName("desktop.png");
        category.setMobileImageName("mobile.png");
        category.setThumbnailImageName("thumbnail.png");

        subCategory=new SubCategory();
        subCategory.setId(1L);
        subCategory.setName("Milk");
        subCategory.setDescription("Milk Description");

        product.setSupplier(supplier);
        product.setCategory(category);
        product.setSubCategory(subCategory);
        List<Product> supplierProducts = new ArrayList<>();
        for (Product product1 :
                supplier.getProducts()) {
            supplierProducts.add(product1);
        }

        request.addHeader("Authentication","");

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));
        Mockito.when(productRepository.existsById(1L)).thenReturn(true);
        Mockito.when(productRepository.findBySupplier(supplier)).thenReturn(supplierProducts);
        Mockito.when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
    }

    @Test
    void createProduct() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductsBySupplier() {
    }

    @Test
    void getByCategoryAndSupplier() {
    }

    @Test
    void getBySubCategoryAndSupplier() {
    }

    @Test
    void searchProduct() {
    }

    @Test
    void searchProductInSupplier() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void increaseUnits() {
    }

    @Test
    void decreaseUnits() {
    }

    @Test
    void uploadImage() {
    }

    @Test
    void downloadImage() {
    }

    @Test
    void createProductOffer() {
    }
}