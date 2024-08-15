package com.mongo.demo.repositories;

import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;


import com.mongo.demo.entities.Purchase;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import static com.mongodb.client.model.ReturnDocument.AFTER;


import jakarta.annotation.PostConstruct;

@Repository
public class MongoPurchaseRepository implements PurchaseRepository {


    private final MongoClient client;
    private MongoCollection<Purchase> purchasesCollection;

    public MongoPurchaseRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    private static final TransactionOptions txnOptions = TransactionOptions.builder().readPreference(ReadPreference.primary()).readConcern(ReadConcern.MAJORITY).writeConcern(WriteConcern.MAJORITY).build();


     @PostConstruct
    void init() {
        purchasesCollection = client.getDatabase("orders").getCollection("persons", Purchase.class);
    }   
    @Override
    public Purchase save(Purchase purchase) {
        purchase.setId(new ObjectId());
        purchasesCollection.insertOne(purchase);
        return purchase;
    }

    @Override
    public List<Purchase> saveAll(List<Purchase> purchases) {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(() -> {
                purchases.forEach(p -> p.setId(new ObjectId()));
                purchasesCollection.insertMany(clientSession, purchases);
                return purchases;
            }, txnOptions);
        }
    }

    @Override
    public List<Purchase> findAll() {
         return purchasesCollection.find().into(new ArrayList<>());
    }

    @Override
    public Purchase findOne(String id) {
        return purchasesCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public long count() {
        return purchasesCollection.countDocuments();
    }

    @Override
    public long delete(String id) {
        return purchasesCollection.deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
    }

    @Override
    public long deleteAll() {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(
                    () -> purchasesCollection.deleteMany(clientSession, new BsonDocument()).getDeletedCount(), txnOptions);
        }
    }

    @Override
    public Purchase update(Purchase purchase) {
         FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        return purchasesCollection.findOneAndReplace(eq("_id", purchase.getId()), purchase, options);
    }

    @Override
    public void dropCollection() {
        purchasesCollection.drop();
        System.out.println("purchases Collection is dropped.");
    }



    
    
}
