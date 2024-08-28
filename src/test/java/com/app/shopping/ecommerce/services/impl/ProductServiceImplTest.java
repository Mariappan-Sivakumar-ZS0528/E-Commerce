package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.entity.Product;
import com.app.shopping.ecommerce.entity.SubCategory;
import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.ProductDto;
import com.app.shopping.ecommerce.payload.ProductOfferDto;
import com.app.shopping.ecommerce.repository.CategoryRepository;
import com.app.shopping.ecommerce.repository.ProductRepository;
import com.app.shopping.ecommerce.repository.SubCategoryRepository;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.services.ProductService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import com.app.shopping.ecommerce.util.ImageDimensionExtractor;
import com.app.shopping.ecommerce.util.ImageUtils;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private SubCategoryRepository subCategoryRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private EmailExtractor emailExtractor;
    @InjectMocks
    private ProductServiceImpl productService;
     Product product;
     ProductDto productdto;
     Supplier supplier;
     Category category;
     SubCategory subCategory;
     MockHttpServletRequest request = new MockHttpServletRequest();
    static MockedStatic<ImageUtils> imageUtilsMockedStatic ;
    static MockedStatic<ImageDimensionExtractor> imageDimensionExtractorMockedStatic;

    @BeforeEach
    void setUp() {
        productdto=new ProductDto();
        product=new Product();
        product.setId(1L);
        product.setName("Cow Milk");
        product.setPrice(100);
        product.setInventory(10);
        product.setQuantity(5);
        product.setDescription("Product Description");
        productdto.setId(1L);
        productdto.setName("Cow Milk");
        productdto.setPrice(100d);
        productdto.setInventory(10);
        productdto.setQuantity(5);
        productdto.setDescription("Product Description");

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
        subCategory.setCategory(category);
        List<Product> supplierProducts = new ArrayList<>(supplier.getProducts());

        request.addHeader("Authentication","");
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.findBySupplier(supplier)).thenReturn(supplierProducts);
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(emailExtractor.getEmailFromRequest(request)).thenReturn("abc@gmail.com");
    }
    @BeforeAll
    static void inisetup() {
       imageUtilsMockedStatic= mockStatic(ImageUtils.class);
       imageDimensionExtractorMockedStatic=mockStatic(ImageDimensionExtractor.class);
    }
    @AfterAll
    static void cleanUp() {
        imageUtilsMockedStatic.close();
        imageDimensionExtractorMockedStatic.close();
    }

    @Test
    void createProductForSuccess() {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        when(categoryRepository.findByName(productdto.getCategoryName())).thenReturn(Optional.of(category));
        when(subCategoryRepository.findByName(productdto.getSubCategoryName())).thenReturn(Optional.of(subCategory));
        when(modelMapper.map(productdto,Product.class)).thenReturn(product);
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        ProductDto savedProduct = productService.createProduct(productdto, request);
        assertEquals(productdto, savedProduct);
    }
    @Test
    void createProductForFailure() {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        SubCategory subCategory1 = new SubCategory();
        subCategory1.setId(2L);
        subCategory1.setName("Milk");
        subCategory1.setDescription("Milk Description");
        Category category1 = new Category();
        category1.setId(2L);
        subCategory1.setCategory(category1);
        when(categoryRepository.findByName(productdto.getCategoryName())).thenReturn(Optional.of(category));
        when(subCategoryRepository.findByName(productdto.getSubCategoryName())).thenReturn(Optional.of(subCategory1));
        when(modelMapper.map(productdto,Product.class)).thenReturn(product);
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        assertThrows(ECommerceApiException.class,()->productService.createProduct(productdto, request));
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        assertEquals(List.of(productdto),productService.getAllProducts());
    }

    @Test
    void getProductById() {
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        assertEquals(productdto,productService.getProductById(1L));
    }

    @Test
    void getProductsBySupplier() {
        when(productRepository.findBySupplier(supplier)).thenReturn(List.of(product));
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        assertEquals(List.of(productdto),productService.getProductsBySupplier(1L));
    }

    @Test
    void getByCategoryAndSupplier() {
        when(categoryRepository.findByName(productdto.getCategoryName())).thenReturn(Optional.of(category));
        when(productRepository.findBySupplier(supplier)).thenReturn(List.of(product));
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        assertEquals(List.of(productdto),productService.getByCategoryAndSupplier(1L,productdto.getCategoryName()));
    }

    @Test
    void getBySubCategoryAndSupplier() {
        when(subCategoryRepository.findByName(productdto.getSubCategoryName())).thenReturn(Optional.of(subCategory));
        when(productRepository.findBySupplier(supplier)).thenReturn(List.of(product));
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        assertEquals(List.of(productdto),productService.getBySubCategoryAndSupplier(1L,productdto.getSubCategoryName()));
    }

    @Test
    void searchProduct() {
        Product product1 = new Product();
        product1.setId(2L);
        product1.setName("kk");
        ProductDto productdto1 = new ProductDto();
        productdto1.setId(2L);
        productdto1.setName("kk");
        when(productRepository.search("kk")).thenReturn(List.of(product1));
        when(supplierRepository.search("kk")).thenReturn(List.of(supplier));
        when(productRepository.findBySupplier(supplier)).thenReturn(List.of(product));
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        when(modelMapper.map(product1,ProductDto.class)).thenReturn(productdto1);
        List<ProductDto> list = new ArrayList<>();
        list.add(productdto1);
        list.add(productdto);
        assertEquals(list,productService.searchProduct("kk"));
    }

    @Test
    void searchProductInSupplier() {
        Product product1 = new Product();
        product1.setId(2L);
        product1.setName("kk");
        product1.setSupplier(new Supplier());
        ProductDto productdto1 = new ProductDto();
        productdto1.setId(2L);
        productdto1.setName("kk");
        when(productRepository.search("kk")).thenReturn(List.of(product,product1));
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        when(modelMapper.map(product1,ProductDto.class)).thenReturn(productdto1);
        assertEquals(List.of(productdto),productService.searchProductInSupplier(1L,"kk"));
    }

    @Test
    void updateProductForSuccess() {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        when(categoryRepository.findByName(productdto.getCategoryName())).thenReturn(Optional.of(category));
        when(subCategoryRepository.findByName(productdto.getSubCategoryName())).thenReturn(Optional.of(subCategory));
        when(modelMapper.map(productdto,Product.class)).thenReturn(product);
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        ProductDto savedProduct = productService.updateProduct(1L,productdto, request);
        product.setName(product.getName());
        product.setDescription(productdto.getDescription());
        product.setPrice(productdto.getPrice());
        product.setQuantity(productdto.getQuantity());
        product.setDiscount(productdto.getDiscount());
        product.setInventory(productdto.getInventory());
        product.setCategory(category);
        product.setSubCategory(subCategory);
        when(modelMapper.map(savedProduct,ProductDto.class)).thenReturn(productdto);
        assertEquals(productdto, savedProduct);
    }
    @Test
    void updateProductForFailure(){
        Category category1 = new Category();
        category1.setId(2L);
        category1.setName("test");
        Supplier supplier1 = new Supplier();
        supplier1.setId(2L);
        supplier1.setEmail("abc@gmail.com");
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier1));
        when(categoryRepository.findByName(productdto.getCategoryName())).thenReturn(Optional.of(category1));
        when(subCategoryRepository.findByName(productdto.getSubCategoryName())).thenReturn(Optional.of(subCategory));
        when(modelMapper.map(productdto,Product.class)).thenReturn(product);
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        assertThrows(ECommerceApiException.class,()->productService.updateProduct(1L,productdto, request));
    }

    @Test
    void deleteProductForSuccess() {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        assertEquals(true, productService.deleteProduct(1L, request));

    }
    @Test
    void deleteProductFordifferentProduct(){
        Product product1 = new Product();
        product1.setId(2L);
        product1.setName("kk");
        product1.setSupplier(new Supplier());
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        assertThrows(ECommerceApiException.class,()->productService.deleteProduct(1L, request));
    }
    @Test
    void deleteProductFordifferentSupplier(){
        Supplier supplier1 = new Supplier();
        supplier1.setId(2L);
        supplier1.setEmail("abc@gmail.com");
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier1));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        assertThrows(ECommerceApiException.class,()->productService.deleteProduct(1L, request));
    }
    @Test
    void deleteProductForFailure(){
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->productService.deleteProduct(1L, request));
    }

    @Test
    void increaseUnitsForSuccess() {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        product.setInventory(product.getInventory()+2);
        when(productRepository.save(product)).thenReturn(product);
        productdto.setInventory(productdto.getInventory()+2);
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        assertEquals(productdto,productService.increaseUnits(1L, request,2));
    }
    @Test
    void increaseUnitsForNullData(){
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->productService.increaseUnits(1L, request,2));
    }

    @Test
    void increaseUnitsForFailure() {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        Product product1 = new Product();
        product1.setId(2L);
        product1.setName("kk");
        product1.setSupplier(new Supplier());
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        assertThrows(ECommerceApiException.class,()->productService.increaseUnits(1L, request,2));
    }

    @Test
    void decreaseUnitsForSuccess() {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        product.setInventory(product.getInventory()-2);
        productdto.setInventory(productdto.getInventory()-2);
        when(productRepository.save(product)).thenReturn(product);
        when(modelMapper.map(product,ProductDto.class)).thenReturn(productdto);
        assertEquals(productdto,productService.decreaseUnits(1L, request,2));
    }
    @Test
    void decreaseUnitsForNullData(){
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->productService.decreaseUnits(1L, request,2));
    }
    @Test
    void decreaseUnitsForFailure(){
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        Product product1 = new Product();
        product1.setId(2L);
        product1.setName("kk");
        product1.setSupplier(new Supplier());
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        assertThrows(ECommerceApiException.class,()->productService.decreaseUnits(1L, request,2));
    }

    @Test
    void uploadImageForSuccess() throws IOException {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        MultipartFile image1 =new MockMultipartFile("image","image".getBytes());
        when(ImageUtils.compressImage(any(byte[].class))).thenReturn(image1.getBytes());
        String imagesAdded = "Images Added: , , , , ";
        assertEquals(imagesAdded,productService.uploadImage(1L, request, image1, image1, image1, image1));
    }

    @Test
    void uploadImageForFailure() throws IOException {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        product.setSupplier(new Supplier());
        MultipartFile image1 =new MockMultipartFile("image","image".getBytes());
        when(ImageUtils.compressImage(any(byte[].class))).thenReturn(image1.getBytes());
        assertThrows(ECommerceApiException.class,()->productService.uploadImage(1L, request, image1, image1, image1, image1));
    }

    @Test
    void uploadImageForMoreData() throws IOException {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        MultipartFile image1 =new MockMultipartFile("image","image".getBytes());
        when(ImageUtils.compressImage(any(byte[].class))).thenReturn(image1.getBytes());
        String imagesAdded = "Images Added: , , , , ";
        assertThrows(ECommerceApiException.class,()->productService.uploadImage(1L, request, image1, image1, image1, image1,image1));
    }

    @Test
    void downloadImage() {
        product.setImageName1("image1");
        product.setImageName2("image2");
        product.setImageName3("image3");
        product.setImageName4("image4");
        product.setImageData1(new byte[]{1,3,55,1});
        product.setImageData2(new byte[]{1,3,55,2});
        product.setImageData3(new byte[]{1,3,55,3});
        product.setImageData4(new byte[]{1,3,55,4});
        byte[] mock1=new byte[]{1, 2, 3, 4, 5};
        byte[] mock2=new byte[]{6, 7, 8, 9, 10};
        byte[] mock3=new byte[]{11, 12, 13, 14, 15};
        byte[] mock4=new byte[]{16, 17, 18, 19, 20};
        when(ImageUtils.decompressImage(product.getImageData1())).thenReturn(mock1);
        when(ImageUtils.decompressImage(product.getImageData2())).thenReturn(mock2);
        when(ImageUtils.decompressImage(product.getImageData3())).thenReturn(mock3);
        when(ImageUtils.decompressImage(product.getImageData4())).thenReturn(mock4);
        assertEquals(mock1,productService.downloadImage(1L,"image1"));
        assertEquals(mock2,productService.downloadImage(1L,"image2"));
        assertEquals(mock3,productService.downloadImage(1L,"image3"));
        assertEquals(mock4,productService.downloadImage(1L,"image4"));
    }

    @Test
    void downloadImageForNullData() {
        product.setImageName1("image1");
        product.setImageName2("image2");
        product.setImageName3("image3");
        product.setImageName4("image4");
        product.setImageData1(new byte[]{1,3,55,1});
        product.setImageData2(new byte[]{1,3,55,2});
        product.setImageData3(new byte[]{1,3,55,3});
        product.setImageData4(new byte[]{1,3,55,4});
        byte[] mock1=new byte[]{1, 2, 3, 4, 5};
        byte[] mock2=new byte[]{6, 7, 8, 9, 10};
        byte[] mock3=new byte[]{11, 12, 13, 14, 15};
        byte[] mock4=new byte[]{16, 17, 18, 19, 20};
        when(ImageUtils.decompressImage(product.getImageData1())).thenReturn(mock1);
        when(ImageUtils.decompressImage(product.getImageData2())).thenReturn(mock2);
        when(ImageUtils.decompressImage(product.getImageData3())).thenReturn(mock3);
        when(ImageUtils.decompressImage(product.getImageData4())).thenReturn(mock4);
        assertEquals(null, productService.downloadImage(1L,"image12"));
    }
    @Test
    void createProductOfferForLimitedOffertype() throws ParseException {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        ProductOfferDto productOfferDto = new ProductOfferDto();
        productOfferDto.setDiscount(10);
        productOfferDto.setOfferDurationType("limited");
        Date startingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-25");
        Date endingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-26");
        productOfferDto.setStartingDate(startingDate);
        productOfferDto.setEndingDate(endingDate);
        when(modelMapper.map(product,ProductOfferDto.class)).thenReturn(productOfferDto);
        assertEquals(productOfferDto,productService.createProductOffer(1L,productOfferDto,request));
    }
    @Test
    void createProductOfferFordifferentSupplier() throws ParseException {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        product.setSupplier(new Supplier());
        ProductOfferDto productOfferDto = new ProductOfferDto();
        productOfferDto.setDiscount(10);
        productOfferDto.setOfferDurationType("limited");
        Date startingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-23");
        Date endingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-26");
        productOfferDto.setStartingDate(startingDate);
        productOfferDto.setEndingDate(endingDate);
        when(modelMapper.map(product,ProductOfferDto.class)).thenReturn(productOfferDto);
        assertThrows(ECommerceApiException.class,()->productService.createProductOffer(1L,productOfferDto,request));
    }
    @Test
    void createProductOfferForStartingdateAfterEndingDate() throws ParseException {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        ProductOfferDto productOfferDto = new ProductOfferDto();
        productOfferDto.setDiscount(10);
        productOfferDto.setOfferDurationType("limited");
        Date startingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-27");
        Date endingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-26");
        productOfferDto.setStartingDate(startingDate);
        productOfferDto.setEndingDate(endingDate);
        when(modelMapper.map(product,ProductOfferDto.class)).thenReturn(productOfferDto);
        assertThrows(ECommerceApiException.class,()->productService.createProductOffer(1L,productOfferDto,request));
    }
    @Test
    void createProductOfferForStartingDateBeforeCurrentDate() throws ParseException {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        ProductOfferDto productOfferDto = new ProductOfferDto();
        productOfferDto.setDiscount(10);
        productOfferDto.setOfferDurationType("limited");
        Date startingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-21");
        Date endingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-26");
        productOfferDto.setStartingDate(startingDate);
        productOfferDto.setEndingDate(endingDate);
        when(modelMapper.map(product,ProductOfferDto.class)).thenReturn(productOfferDto);
        assertThrows(ECommerceApiException.class,()->productService.createProductOffer(1L,productOfferDto,request));
    }
    @Test
    void createProductOfferForDealOfTheDay() throws ParseException {
        when(supplierRepository.findByEmail("abc@gmail.com")).thenReturn(Optional.of(supplier));
        ProductOfferDto productOfferDto = new ProductOfferDto();
        productOfferDto.setDiscount(10);
        productOfferDto.setOfferDurationType("DealOfTheDay");
        Date startingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-23");
        Date endingDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-26");
        productOfferDto.setStartingDate(startingDate);
        productOfferDto.setEndingDate(endingDate);
        when(modelMapper.map(product,ProductOfferDto.class)).thenReturn(productOfferDto);
        assertEquals(productOfferDto,productService.createProductOffer(1L,productOfferDto,request));
    }

    @Test
    void getProductOfferByIdForSuccess() throws ParseException {
        ProductOfferDto productOfferDto = new ProductOfferDto();
        product.setStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-20"));
        product.setEndingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-07-26"));
        when(modelMapper.map(product,ProductOfferDto.class)).thenReturn(productOfferDto);
        assertEquals(productOfferDto,productService.getProductOfferById(1L));
    }
    @Test
    void getProductOfferByIdForFailure() throws ParseException {
        ProductOfferDto productOfferDto = new ProductOfferDto();
        product.setStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-08-21"));
        product.setEndingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-08-26"));
        when(modelMapper.map(product,ProductOfferDto.class)).thenReturn(productOfferDto);
        assertThrows(ECommerceApiException.class,()->productService.getProductOfferById(1L));
    }
}