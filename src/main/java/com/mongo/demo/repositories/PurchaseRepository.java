package com.mongo.demo.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongo.demo.entities.Purchase;

@Repository
public interface PurchaseRepository{
    
    Purchase save(Purchase purchase);

    List<Purchase> saveAll(List<Purchase> purchases);

    List<Purchase> findAll();

    Purchase findOne(Purchase purchase);

    long count();

    long delete(int id);

    long deleteAll();

    void dropCollection();

    Purchase update(Purchase purchase);

    List<Purchase> firstPipeline();

    List<Purchase> secondPipeline();



}