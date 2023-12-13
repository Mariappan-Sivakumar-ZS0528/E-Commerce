package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.Cart;
import com.app.shopping.ecommerce.entity.Customer;
import com.app.shopping.ecommerce.entity.Product;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.CartDto;
import com.app.shopping.ecommerce.repository.CartRepository;
import com.app.shopping.ecommerce.repository.CustomerRepository;
import com.app.shopping.ecommerce.repository.ProductRepository;
import com.app.shopping.ecommerce.services.CartService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private EmailExtractor emailExtractor;
    private CartRepository cartRepository;
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private ProductRepository productRepository;

    public CartServiceImpl(EmailExtractor emailExtractor, CartRepository cartRepository, CustomerRepository customerRepository, ModelMapper modelMapper, ProductRepository productRepository) {
        this.emailExtractor = emailExtractor;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public CartDto addToCart(CartDto cartDto, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer", "email", email));
        Cart cart = new Cart();
        Product product= productRepository.findById(cartDto.getProductId()).orElseThrow(()->new ResourceNotFoundException("Product", "id", cartDto.getProductId()));
        if(product.getInventory()<cartDto.getQuantity()){
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "Out of stock");
        }
        cart.setProduct(product);
        cart.setQuantity(cartDto.getQuantity());
        cart.setCustomer(customer);
        Cart cart1=cartRepository.save(cart);
        return modelMapper.map(cart1, CartDto.class);
    }

    @Override
    public List<CartDto> getAllCart(HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer", "email", email));
        List<Cart> carts = cartRepository.findByCustomer(customer);
        return carts.stream().map(cart -> modelMapper.map(cart, CartDto.class)).toList();
    }
}
