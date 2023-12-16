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
import com.app.shopping.ecommerce.util.DateUtil;
import com.app.shopping.ecommerce.util.EmailExtractor;
import com.app.shopping.ecommerce.util.ImageUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private CategoryRepository categoryRepository;
    private SubCategoryRepository subCategoryRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private EmailExtractor emailExtractor;
    private SupplierRepository supplierRepository;
    private DateUtil dateUtil = new DateUtil();
    Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);
    public ProductServiceImpl(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository, ProductRepository productRepository, ModelMapper modelMapper, EmailExtractor emailExtractor, SupplierRepository supplierRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.emailExtractor = emailExtractor;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Supplier", "email", email));
        logger.info(productDto.getCategoryName());
        Category category = categoryRepository.findByName(productDto.getCategoryName()).orElseThrow(()-> new ResourceNotFoundException("Category", "name", productDto.getCategoryName()));
        SubCategory subCategory = subCategoryRepository.findByName(productDto.getSubCategoryName()).orElseThrow(()-> new ResourceNotFoundException("SubCategory", "name", productDto.getSubCategoryName()));
        if (!subCategory.getCategory().equals(category)){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"SubCategory should be inside same category");
        }
        Product product=modelMapper.map(productDto, Product.class);
        product.setCategory(category);
        product.setSubCategory(subCategory);
        product.setSupplier(supplier);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(product -> modelMapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", "id", id));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductsBySupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(()-> new ResourceNotFoundException("Supplier", "id", supplierId));
        return productRepository.findBySupplier(supplier).stream().map(product -> modelMapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public List<ProductDto> getByCategoryAndSupplier(Long supplierId, String category) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(()-> new ResourceNotFoundException("Supplier", "id", supplierId));
        Category category1 = categoryRepository.findByName(category).orElseThrow(()-> new ResourceNotFoundException("Category", "name", category));
        return productRepository.findBySupplier(supplier).stream().filter(product -> product.getCategory().equals(category1)).map(product -> modelMapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public List<ProductDto> getBySubCategoryAndSupplier(Long id, String subCategory) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Supplier", "id", id));
        SubCategory subCategory1 = subCategoryRepository.findByName(subCategory).orElseThrow(()-> new ResourceNotFoundException("SubCategory", "name", subCategory));
        return productRepository.findBySupplier(supplier).stream().filter(product -> product.getSubCategory().equals(subCategory1)).map(product -> modelMapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public List<ProductDto> searchProduct(String query) {
        List<Product> products = productRepository.search(query);
        List<Supplier> suppliers = supplierRepository.search(query); //<-->
        if (!(suppliers.isEmpty())){
            for (Supplier supplier : suppliers) {
                products.addAll(productRepository.findBySupplier(supplier));
            }
        }
        Set<Product> set = Set.copyOf(products); //<-->
        return set.stream().map(product -> modelMapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public List<ProductDto> searchProductInSupplier(Long id,String query) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Supplier", "id", id));
        List<Product> products = productRepository.search(query);
        return products.stream().filter(product -> product.getSupplier().equals(supplier)).map(product -> modelMapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public ProductDto updateProduct(Long productId,ProductDto productDto, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Supplier", "email", email));
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));
        Category category = categoryRepository.findByName(productDto.getCategoryName()).orElseThrow(()-> new ResourceNotFoundException("Category", "name", productDto.getCategoryName()));
        SubCategory subCategory = subCategoryRepository.findByName(productDto.getSubCategoryName()).orElseThrow(()-> new ResourceNotFoundException("SubCategory", "name", productDto.getSubCategoryName()));
        if (!(subCategory.getCategory().equals(category) || product.getSupplier().equals(supplier))){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"SubCategory should be inside same category");
        }
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDiscount(productDto.getDiscount());
        product.setInventory(productDto.getInventory());
        product.setCategory(category);
        product.setSubCategory(subCategory);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(Long productId, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Supplier", "email", email));
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));
        if (! product.getSupplier().equals(supplier)){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"Product cannot be deleted");
        }
        productRepository.delete(product);
    }

    @Override
    public ProductDto increaseUnits(Long productId, HttpServletRequest request, int units) {
        logger.info(productId + " " + units);
        String email=emailExtractor.getEmailFromRequest(request);
        logger.info(email);
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Supplier", "email", email));
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));
        if (! product.getSupplier().equals(supplier)){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"Product cannot be accessed");
        }
        product.setInventory(product.getInventory()+units);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto decreaseUnits(Long productId, HttpServletRequest request, int units) {
        String email=emailExtractor.getEmailFromRequest(request);
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Supplier", "email", email));
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));
        if (! product.getSupplier().equals(supplier)){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"Product cannot be accessed");
        }
        product.setInventory(product.getInventory()-units);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public String uploadImage(Long productId, HttpServletRequest request, MultipartFile... images) throws IOException {
        String email=emailExtractor.getEmailFromRequest(request);
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Supplier", "email", email));
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));
        if (! product.getSupplier().equals(supplier)){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"Product cannot be accessed");
        }
        if (images.length>4){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"You have give more images");
        }
        String imagesAdded="Images Added: ";
        if (images.length>0){
            product.setImageData1(ImageUtils.compressImage(images[0].getBytes()));
            product.setImageName1(images[0].getOriginalFilename());
            imagesAdded+=images[0].getOriginalFilename()+", ";
        }
        if (images.length>1){
            product.setImageData2(ImageUtils.compressImage(images[1].getBytes()));            product.setImageName2(images[1].getOriginalFilename());
            imagesAdded+=images[1].getOriginalFilename()+", ";
        }
        if (images.length>2){
            product.setImageData3(ImageUtils.compressImage(images[2].getBytes()));            product.setImageName3(images[2].getOriginalFilename());
            imagesAdded+=images[2].getOriginalFilename()+", ";
        }
        if (images.length>3){
            product.setImageData3(ImageUtils.compressImage(images[3].getBytes()));            product.setImageName4(images[3].getOriginalFilename());
            imagesAdded+=images[3].getOriginalFilename()+", ";
        }
        productRepository.save(product);
        return imagesAdded;
    }

    @Override
    public byte[] downloadImage(Long productId,String imageName) {
         Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));
        if (imageName.equals(product.getImageName1())){
            return ImageUtils.decompressImage(product.getImageData1());
        } else if (imageName.equals(product.getImageName2())) {
            return ImageUtils.decompressImage(product.getImageData2());
        } else if (imageName.equals(product.getImageName3())){
            return ImageUtils.decompressImage(product.getImageData3());
        } else if (imageName.equals(product.getImageName4())){
            return ImageUtils.decompressImage(product.getImageData4());
        } else {
            return null;
        }
    }

    @Override
    public ProductOfferDto createProductOffer(Long productId, ProductOfferDto productOfferDto, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Supplier supplier = supplierRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Supplier", "email", email));
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));
        if (! product.getSupplier().equals(supplier)){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"Product cannot be accessed");
        }
        product.setDiscount(productOfferDto.getDiscount());
        product.setOfferDurationType(productOfferDto.getOfferDurationType());
        if (product.getOfferDurationType().equals("limited")){
            product.setStartingDate(productOfferDto.getStartingDate());
            product.setEndingDate(productOfferDto.getEndingDate());
            if (product.getStartingDate().after(product.getEndingDate())){
                throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"Starting date should be before ending date");
            }
            if (product.getStartingDate().before(dateUtil.getStartOfDay())||product.getEndingDate().before(new Date())){
                throw new ECommerceApiException(HttpStatus.BAD_REQUEST,"Starting date should be after current date");
            }
        }
        else if (product.getOfferDurationType().equals("DealOfTheDay")){
            product.setStartingDate(dateUtil.getStartOfDay());
            product.setEndingDate(dateUtil.getEndOfDay());
        }
        product.setActive(product.isActive());
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductOfferDto.class);
    }
}
