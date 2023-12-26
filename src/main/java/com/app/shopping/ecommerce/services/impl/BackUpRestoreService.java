package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.exception.ECommerceApiException;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class BackUpRestoreService {

//    private AddressBookRepository addressBookRepository;
//    private JpaRepository<T, Long> repository;
    private ApplicationContext applicationContext;

    public BackUpRestoreService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
//
//    public SqlGenerator(AddressBookRepository addressBookRepository, JpaRepository<T, Long> repository) {
//        this.addressBookRepository = addressBookRepository;
//        this.repository = repository;
//    }

//    public void addressBookSqlGenerator() {
//        List<AddressBook> addressBooks = addressBookRepository.findAll();
//        try(FileOutputStream fileOutputStream=new FileOutputStream("src/main/resources/address-book.bin")) {
//            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(addressBooks);
//        } catch (IOException e) {
//            throw new ECommerceApiException(HttpStatus.INTERNAL_SERVER_ERROR,"Cannot create Backup file file");
//        }
//    }
//    public void AddressBookSqlRun(){
//        List<AddressBook> addressBooks = new ArrayList<>();
//        try(FileInputStream fileInputStream=new FileInputStream("src/main/resources/address-book.bin")) {
//            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
//            addressBooks = (List<AddressBook>) objectInputStream.readObject();
//        } catch (ClassNotFoundException | IOException e) {
//            throw new RuntimeException(e);
//        }
//        addressBooks.forEach(addressBook -> addressBook.setId(null));
//        addressBookRepository.saveAll(addressBooks);
//    }
    public <T> int fileGenerator(String entityName) {
        JpaRepository<T, Long> repository = getRepository(entityName);
        List<T> entities = repository.findAll();
        try(FileOutputStream fileOutputStream=new FileOutputStream("src/main/resources/"+entityName+".bin")) {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities);
        } catch (IOException e) {
            throw new ECommerceApiException(HttpStatus.INTERNAL_SERVER_ERROR,"Cannot create Backup file file");
        }
        return entities.size();
    }
    public <T> void fileRun(String entityName) {
        List<T> entities;
        JpaRepository<T, Long> repository = getRepository(entityName);
        try(FileInputStream fileInputStream=new FileInputStream("src/main/resources/"+entityName+".bin")) {
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            entities = (List<T>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        entities.forEach(entity -> {
            try {
                entity.getClass().getMethod("setId", Long.class).invoke(entity, (Long) null);
//                System.out.println(entity.getClass().getMethod("getCity").invoke(entity));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        repository.saveAll(entities);
    }
    private <T> JpaRepository<T, Long> getRepository(String entityName) {
        String repositoryBeanName = entityName+"Repository";
        return applicationContext.getBean(repositoryBeanName, JpaRepository.class);
    }
}
