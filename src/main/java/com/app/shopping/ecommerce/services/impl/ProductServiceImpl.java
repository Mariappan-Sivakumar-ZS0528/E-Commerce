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

import java.util.List;

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

    @Override
    public ProductDto getProductById(Long id, HttpServletRequest request) {
        return null;
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
        product.setUnit(productDto.getUnit());
        product.setDiscount(productDto.getDiscount());
        product.setUnit(productDto.getUnit());
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
}
