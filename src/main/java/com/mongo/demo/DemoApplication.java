package com.mongo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import static com.mongodb.client.model.Filters.eq;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DemoApplication{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		String uri = "mongodb+srv://aziz:aziz@aziz-mongodb-cluster.mrdntek.mongodb.net/?retryWrites=true&w=majority&appName=Aziz-MongoDB-Cluster";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("orders");
            MongoCollection<Document> collection = database.getCollection("purchases");
            Document doc = collection.find(eq("purchase","clothes")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
	}

}
}
