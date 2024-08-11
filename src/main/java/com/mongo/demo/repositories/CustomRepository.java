package com.mongo.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongo.demo.entities.Purchase;
import com.mongodb.client.result.UpdateResult;

@Component
public class CustomRepository implements ICustomRepository {
   
    @Autowired
	MongoTemplate mongoTemplate;

    @Override
    public void updatePurchaseName(Integer id, String newName) {
        
        Query query = new Query(Criteria.where("_id").is(id));
		Update update = new Update();
		update.set("purchase", newName);


        UpdateResult result = mongoTemplate.updateFirst(query, update, Purchase.class);
		
		if(result == null)
			System.out.println("No documents updated");
		else
			System.out.println(result.getModifiedCount() + " document(s) updated..");

    }
}
