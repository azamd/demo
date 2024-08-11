package com.mongo.demo.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;

import com.mongo.demo.entities.Purchase;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PurchaseRepository extends MongoRepository<Purchase,Integer> {
    
    @Query("{_id:?0}")
    Purchase findPurchaseById(Integer id);
    
    @Query(value="{}", fields="{'_id' : 1, 'purchase' : 1}")
    List<Purchase> findAll();

    public long count();
    

}