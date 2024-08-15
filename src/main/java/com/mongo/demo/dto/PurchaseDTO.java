package com.mongo.demo.dto;

import org.bson.types.ObjectId;
import com.mongo.demo.entities.Purchase;

public class PurchaseDTO {

    private String id;
    private String purchase;

    public PurchaseDTO() {
    }

    public PurchaseDTO(Purchase p) {
        this.id = (p.getId() == null) ? new ObjectId().toHexString() : p.getId().toHexString();
        this.purchase = p.getPurchase();
    }

    public PurchaseDTO(String id, String purchase) {
        this.id = id;
        this.purchase = purchase;
    }

    public Purchase toPurchase() {
        ObjectId _id = (id == null) ? new ObjectId() : new ObjectId(id);
        return new Purchase(_id, purchase);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    @Override
    public String toString() {
        return "PurchaseDTO{" +
                "id='" + id + '\'' +
                ", purchase='" + purchase + '\'' +
                '}';
    }
}
