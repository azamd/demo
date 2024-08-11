/*package com.mongo.demo.repositories;
import java.util.List;

import org.springframework.data.ldap.repository.Query;

import com.mongo.demo.entities.Purchase;

public interface PurchaseRepository extends MongoRepository<Purchase,String> {
    
    @Query("{name:'?0'}")
    Purchase findPurchaseByName(String name);
    
    @Query
    List<Purchase> findAll();
    

}*/