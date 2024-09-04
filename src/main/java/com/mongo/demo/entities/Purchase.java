package com.mongo.demo.entities;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    
    private ObjectId id;
    private String purchase;
    private String color;
    private float price;
    private Date date = new Date();
    private List<String> size;

    

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }
  
}