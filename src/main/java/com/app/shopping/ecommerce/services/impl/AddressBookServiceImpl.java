package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.AddressBook;
import com.app.shopping.ecommerce.entity.Customer;
import com.app.shopping.ecommerce.exception.ECommerceApiException;
import com.app.shopping.ecommerce.exception.ResourceNotFoundException;
import com.app.shopping.ecommerce.payload.AddressBookDto;
import com.app.shopping.ecommerce.repository.AddressBookRepository;
import com.app.shopping.ecommerce.repository.CustomerRepository;
import com.app.shopping.ecommerce.services.AddressBookService;
import com.app.shopping.ecommerce.util.EmailExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AddressBookServiceImpl implements AddressBookService {
    private EmailExtractor emailExtractor;
    private AddressBookRepository addressBookRepository;
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    Logger logger= LoggerFactory.getLogger(AddressBookServiceImpl.class);
    public AddressBookServiceImpl(EmailExtractor emailExtractor, AddressBookRepository addressBookRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.emailExtractor = emailExtractor;
        this.addressBookRepository = addressBookRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressBookDto createAddressBook(AddressBookDto addressBookDto, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer", "email", email));
        AddressBook addressBook = new AddressBook();
        addressBook.setAddressLine1(addressBookDto.getAddressLine1());
        addressBook.setAddressLine2(addressBookDto.getAddressLine2());
        addressBook.setAddressLine3(addressBookDto.getAddressLine3());
        addressBook.setCity(addressBookDto.getCity());
        addressBook.setPostalCode(addressBookDto.getPostalCode());
        addressBook.setNickName(addressBookDto.getNickName());
        addressBook.setCustomer(customer);
        addressBook.setLabel(addressBookDto.getLabel());
        if (customer.getAddressBooks().isEmpty()) {
            addressBook.setDefault(true);
        }
        AddressBook addressBook1=addressBookRepository.save(addressBook);
        logger.info(addressBook.getAddressLine1()+" "+addressBook1.getAddressLine1());
        return modelMapper.map(addressBook1, AddressBookDto.class);
    }

    @Override
    public AddressBookDto getAddressBookById(Long id, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer", "email", email));
        AddressBook addressBook = addressBookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("AddressBook", "id", id));
        if(!addressBook.getCustomer().getId().equals(customer.getId())){
            throw new ECommerceApiException(HttpStatus.UNAUTHORIZED,"You didn't have the access. Access Denied");
        }
        return modelMapper.map(addressBook, AddressBookDto.class);
    }

    @Override
    public List<AddressBookDto> getAllAddressBook(HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer", "email", email));
        List<AddressBook> addressBook=addressBookRepository.findByCustomerEmail(email);
        return addressBook.stream().map(addressBook1 -> modelMapper.map(addressBook1, AddressBookDto.class)).toList();
    }

    @Override
    public AddressBookDto updateAddressBook(Long id, AddressBookDto addressBookDto, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer", "email", email));
        AddressBook addressBook = addressBookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("AddressBook", "id", id));
        if(!addressBook.getCustomer().equals(customer)){
            throw new ECommerceApiException(HttpStatus.UNAUTHORIZED,"You didn't have the access. Access Denied");
        }
        addressBook.setAddressLine1(addressBookDto.getAddressLine1());
        addressBook.setAddressLine2(addressBookDto.getAddressLine2());
        addressBook.setAddressLine3(addressBookDto.getAddressLine3());
        addressBook.setCity(addressBookDto.getCity());
        addressBook.setPostalCode(addressBookDto.getPostalCode());
        addressBook.setNickName(addressBookDto.getNickName());
        addressBook.setLabel(addressBookDto.getLabel());
        return modelMapper.map(addressBookRepository.save(addressBook), AddressBookDto.class);
    }

    @Override
    public String deleteAddressBook(Long id, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer", "email", email));
        AddressBook addressBook = addressBookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("AddressBook", "id", id));
        if(!addressBook.getCustomer().equals(customer)){
            throw new ECommerceApiException(HttpStatus.UNAUTHORIZED,"You didn't have the access. Access Denied");
        }
        addressBookRepository.deleteById(id);
        return "Address Deleted Successfully";
    }

    @Override
    public String setDefault(Long id, HttpServletRequest request) {
        String email=emailExtractor.getEmailFromRequest(request);
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Customer", "email", email));
        AddressBook addressBook = addressBookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("AddressBook", "id", id));
        if(!addressBook.getCustomer().equals(customer)){
            throw new ECommerceApiException(HttpStatus.UNAUTHORIZED,"You didn't have the access. Access Denied");
        }
        Set<AddressBook> addressBooks = customer.getAddressBooks();
        for (AddressBook addressBook1 : addressBooks) {
            addressBook1.setDefault(false);
        }
        addressBookRepository.saveAll(addressBooks);
        addressBook.setDefault(true);
        addressBookRepository.save(addressBook);
        return "Address Set as Default";
    }
}
