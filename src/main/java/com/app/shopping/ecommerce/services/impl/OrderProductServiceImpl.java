package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.*;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.CheckoutDto;
import com.app.shopping.ecommerce.repository.CartRepository;
import com.app.shopping.ecommerce.repository.CustomerRepository;
import com.app.shopping.ecommerce.repository.OrderProductRepository;
import com.app.shopping.ecommerce.repository.OrderRepository;
import com.app.shopping.ecommerce.services.AddressBookService;
import com.app.shopping.ecommerce.services.OrderProductService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    private OrderProductRepository orderProductRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private CartRepository cartRepository;
    private EmailExtractor emailExtractor;
    private ModelMapper modelMapper;
    private AddressBookService addressBookService;
    Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);
    public OrderProductServiceImpl(OrderProductRepository orderProductRepository, OrderRepository orderRepository, CustomerRepository customerRepository, CartRepository cartRepository, EmailExtractor emailExtractor, ModelMapper modelMapper, AddressBookService addressBookService) {
        this.orderProductRepository = orderProductRepository;
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.emailExtractor = emailExtractor;
        this.addressBookService = addressBookService;
    }

    @Override
    public String checkout(HttpServletRequest request, CheckoutDto checkout) {
        Order order=new Order();
        logger.info(checkout.getAddressBookId()+"hi");
        AddressBook addressBook=modelMapper.map(addressBookService.getAddressBookById(checkout.getAddressBookId(),request),AddressBook.class);
        order.setLocation(addressBook.toString());
        order.setDeliveryOn(checkout.getDeliveryOn());
        String email = emailExtractor.getEmailFromRequest(request);
        logger.info(email);
        logger.info("hi");
        Customer customer=customerRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer","email",email));
        order.setOrderBy(customer.getName());
        double price=0;
        logger.info(customer.getEmail());
        logger.info(email);
        logger.info("hi");
        List<Cart> cart=cartRepository.findAllByCustomerId(customer.getId());
        List<Product> products=cart.stream().map(Cart::getProduct).toList();
        List<OrderProduct> demoOp1=new ArrayList<>();
        for(int i=0;i<products.size();i++){
            OrderProduct op=new OrderProduct();
            op.setProduct(products.get(i));
            logger.info(String.valueOf(products.get(i).getId()));
            op.setQuantity(cartRepository.findByProductIdAndCustomerId(products.get(i).getId(),customer.getId()).getQuantity());
            op.setPrice(products.get(i).getPrice()*cartRepository.findByProductIdAndCustomerId(products.get(i).getId(),customer.getId()).getQuantity());
            price+=op.getPrice();
            logger.info(String.valueOf(op.getQuantity()));
            op.setOrder(order);
            demoOp1.add(op);
            cartRepository.deleteByProductIdAndCustomerId(products.get(i).getId(),customer.getId());
        }
        order.setItems(products.size());
        order.setStatus("Pending");
        order.setPlacedOn(new Date());
        order.setPrice(price);
        order.setOrderProducts(demoOp1);
        order.setCustomer(customer);
        Order order1=orderRepository.save(order);
        return "done";
    }
}
