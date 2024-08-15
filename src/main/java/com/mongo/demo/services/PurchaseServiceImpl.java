package com.mongo.demo.services;

import java.util.List;

import com.mongo.demo.dto.PurchaseDTO;
import com.mongo.demo.repositories.PurchaseRepository;

public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public PurchaseDTO save(PurchaseDTO PurchaseDTO) {
        return new PurchaseDTO(purchaseRepository.save(PurchaseDTO.toPurchase()));
    }

    @Override
    public List<PurchaseDTO> saveAll(List<PurchaseDTO> purchases) {
        return purchases.stream().map(PurchaseDTO::toPurchase).peek(purchaseRepository::save).map(PurchaseDTO::new).toList();
    }

    @Override
    public List<PurchaseDTO> findAll() {
        return purchaseRepository.findAll().stream().map(PurchaseDTO::new).toList();
    }

    @Override
    public PurchaseDTO findOne(String id) {
        return new PurchaseDTO(purchaseRepository.findOne(id));
    }

    @Override
    public long count() {
        return purchaseRepository.count();
    }

    @Override
    public long delete(String id) {
        return purchaseRepository.delete(id);
    }


    @Override
    public long deleteAll() {
        return purchaseRepository.deleteAll();
    }

    @Override
    public PurchaseDTO update(PurchaseDTO PurchaseDTO) {
        return new PurchaseDTO(purchaseRepository.update(PurchaseDTO.toPurchase()));
    }

   
    @Override
    public void dropCollection() {
        purchaseRepository.dropCollection();
    }
    
}
