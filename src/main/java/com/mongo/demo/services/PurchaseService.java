package com.mongo.demo.services;

import java.util.List;

import com.mongo.demo.dto.*;


public interface PurchaseService {
    
    PurchaseDTO save(PurchaseDTO purchaseDTO);

    List<PurchaseDTO> saveAll(List<PurchaseDTO> purchases);

    List<PurchaseDTO> findAll();

    PurchaseDTO findOne(String id);

    long count();

    long delete(String id);

    long deleteAll();

    PurchaseDTO update(PurchaseDTO PurchaseDTO);

    void dropCollection();
    
}
