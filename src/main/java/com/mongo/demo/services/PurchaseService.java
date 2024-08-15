package com.mongo.demo.services;

import java.util.List;

import com.mongo.demo.entities.*;


public interface PurchaseService {
    
    Purchase save(Purchase purchase);

    List<Purchase> saveAll(List<Purchase> purchases);

    List<Purchase> findAll();

    Purchase findOne(Purchase purchase);

    long count();

    long delete(Purchase purchase);

    long deleteAll();

    Purchase update(Purchase PurchaseDTO);

    void dropCollection();
    
}
