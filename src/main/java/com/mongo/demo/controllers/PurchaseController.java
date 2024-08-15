package com.mongo.demo.controllers;


import java.util.List;
import com.mongo.demo.services.*;
import com.mongo.demo.dto.*;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

@RestController
@RequestMapping("/orders")
public class PurchaseController {



private final static Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseDTO postPurchase(@RequestBody PurchaseDTO PurchaseDTO) {
        return purchaseService.save(PurchaseDTO);
    }

    @PostMapping("purchases")
    @ResponseStatus(HttpStatus.CREATED)
    public List<PurchaseDTO> postPurchases(@RequestBody List<PurchaseDTO> purchases) {
        return purchaseService.saveAll(purchases);
    }

    @GetMapping("purchases")
    public List<PurchaseDTO> getPurchases() {
        return purchaseService.findAll();
    }

    @GetMapping("purchase/{id}")
    public ResponseEntity<PurchaseDTO> getPurchase(@PathVariable String id) {
        PurchaseDTO PurchaseDTO = purchaseService.findOne(id);
        if (PurchaseDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(PurchaseDTO);
    }


    @GetMapping("purchases/count")
    public Long getCount() {
        return purchaseService.count();
    }

    @DeleteMapping("purchase/{id}")
    public Long deletePurchase(@PathVariable String id) {
        return purchaseService.delete(id);
    }

    @DeleteMapping("purchases")
    public Long deletePurchases() {
        return purchaseService.deleteAll();
    }

    @PutMapping("purchase")
    public PurchaseDTO updatePurchase(@RequestBody PurchaseDTO PurchaseDTO) {
        return purchaseService.update(PurchaseDTO);
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