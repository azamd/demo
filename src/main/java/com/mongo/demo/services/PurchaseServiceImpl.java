package com.mongo.demo.services;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mongo.demo.entities.Purchase;
import com.mongo.demo.repositories.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase save(Purchase Purchase) {
        return purchaseRepository.save(Purchase);
    }

    @Override
    public List<Purchase> saveAll(List<Purchase> purchases) {
        return purchaseRepository.saveAll(purchases);
    }

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase findOne(Purchase purchase) {
        return purchaseRepository.findOne(purchase);
    }

    @Override
    public long count() {
        return purchaseRepository.count();
    }

    @Override
    public long delete(Purchase purchase) {
        return purchaseRepository.delete(purchase);
    }


    @Override
    public long deleteAll() {
        return purchaseRepository.deleteAll();
    }

    @Override
    public Purchase update(Purchase Purchase) {
        return (purchaseRepository.update(Purchase));
    }

   
    @Override
    public void dropCollection() {
        purchaseRepository.dropCollection();
    }

    @Override
    @Scheduled(fixedRate = 2000)
    public void saveper2sec(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    
    
}
