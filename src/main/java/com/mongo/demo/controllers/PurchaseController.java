package com.mongo.demo.controllers;


import java.util.List;
import com.mongo.demo.services.*;
import com.mongo.demo.entities.*;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

@RestController
@RequestMapping("/api")
public class PurchaseController {



private final static Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase postPurchase(@RequestBody Purchase Purchase) {
        return purchaseService.save(Purchase);
    }

    @PostMapping("purchases")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Purchase> postPurchases(@RequestBody List<Purchase> purchases) {
        return purchaseService.saveAll(purchases);
    }

    @GetMapping("purchases")
    public List<Purchase> getPurchases() {
        return purchaseService.findAll();
    }

    @GetMapping("purchase_byId")
    public Purchase getPurchase(@RequestBody Purchase Purchase) {
        return purchaseService.findOne(Purchase);
    }


    @GetMapping("purchases/count")
    public Long getCount() {
        return purchaseService.count();
    }

    @DeleteMapping("purchase_byId")
    public Long deletePurchase(@RequestBody Purchase Purchase) {
        return purchaseService.delete(Purchase);
    }

    @DeleteMapping("purchases")
    public Long deletePurchases() {
        return purchaseService.deleteAll();
    }

    @PutMapping("purchase")
    public Purchase updatePurchase(@RequestBody Purchase Purchase) {
        return purchaseService.update(Purchase);
    }

    @GetMapping("purchases/drop")
    public void dropCollection(){
       purchaseService.dropCollection();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }

}