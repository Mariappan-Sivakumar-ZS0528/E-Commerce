package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Category;
import com.app.shopping.ecommerce.entity.Product;
import com.app.shopping.ecommerce.entity.SubCategory;
import com.app.shopping.ecommerce.entity.Supplier;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.ProductDto;
import com.app.shopping.ecommerce.repository.CategoryRepository;
import com.app.shopping.ecommerce.repository.ProductRepository;
import com.app.shopping.ecommerce.repository.SubCategoryRepository;
import com.app.shopping.ecommerce.repository.SupplierRepository;
import com.app.shopping.ecommerce.services.ProductService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private CategoryRepository categoryRepository;
    private SubCategoryRepository subCategoryRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private EmailExtractor emailExtractor;
    private SupplierRepository supplierRepository;
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
}
