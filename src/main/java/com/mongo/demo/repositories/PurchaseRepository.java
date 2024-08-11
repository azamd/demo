package com.mongo.demo.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;

import com.mongo.demo.entities.Purchase;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PurchaseRepository extends MongoRepository<Purchase,Integer> {
    
    @Query("{name:'?0'}")
    Purchase findPurchaseById(Integer id);
    
    @Query
    List<Purchase> findAll();

    public long count();
    

}