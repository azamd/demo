package com.mongo.demo.repositories;

import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.stereotype.Repository;


import com.mongo.demo.entities.Purchase;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.Sorts;

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
        purchasesCollection = client.getDatabase("orders").getCollection("purchases", Purchase.class);
    }   
    @Override
    public Purchase save(Purchase purchase) {
        //purchase.setId(0);
        purchasesCollection.insertOne(purchase);
        return purchase;
    }

    @Override
    public List<Purchase> saveAll(List<Purchase> purchases) {
        
                purchasesCollection.insertMany(purchases);
                return purchases;
            };

    

    @Override
    public List<Purchase> findAll() {
         return purchasesCollection.find().into(new ArrayList<>());
    }

    @Override
    public Purchase findOne(Purchase purchase) {
        Document filter = new Document("_id", purchase.getId());
        return purchasesCollection.find(filter).first();
    }

    @Override
    public long count() {
        return purchasesCollection.countDocuments();
    }

    @Override
    public long delete(int id) {
        Document filter = new Document("_id", id);
        return purchasesCollection.deleteOne(filter).getDeletedCount();
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

    @Override
    public void firstPipeline() { 
        
    purchasesCollection.aggregate( Arrays.asList(
    
    Aggregates.match(Filters.exists("size", true)),

    Aggregates.group("$color", Accumulators.push("purchases", 
        new Document("purchase", "$purchase")
            .append("color", "$color")
            .append("price", "$price")
            .append("date", "$date")
            .append("size", "$size"))),

    Aggregates.unwind("$purchases"),

    Aggregates.sort(Sorts.orderBy(Sorts.ascending("purchases.price"), Sorts.descending("purchases.date"))),


    Aggregates.group("$_id", Accumulators.push("sortedPurchases", "$purchases")) )
    
);}
    
    @Override
    public void secondPipeline() { purchasesCollection.aggregate( Arrays.asList(
        
        Aggregates.match(Filters.exists("size", true)),

        Aggregates.group("$size", Accumulators.push("purchases", 
        new Document("purchase", "$purchase")
            .append("color", "$color")
            .append("price", "$price")
            .append("date", "$date")
            .append("size", "$size"))),

        Aggregates.group("$_id", Accumulators.push("sortedPurchases", "$purchases"))
    
)); }

}
