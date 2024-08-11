package com.mongo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

import com.mongo.demo.repositories.PurchaseRepository;
import com.mongo.demo.repositories.CustomRepository;


import com.mongo.demo.entities.Purchase;

@SpringBootApplication
@EnableMongoRepositories
public class DemoApplication implements CommandLineRunner{

	@Autowired
    PurchaseRepository PurchaseRepository;

	@Autowired
    CustomRepository CustomRepository;

	List<Purchase> purchases = new ArrayList<Purchase>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		/*String uri = "mongodb+srv://aziz:aziz@aziz-mongodb-cluster.mrdntek.mongodb.net/?retryWrites=true&w=majority&appName=Aziz-MongoDB-Cluster";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("orders");
            MongoCollection<Document> collection = database.getCollection("purchases");
            Document doc = collection.find(eq("purchase","electronics")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }*/
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		System.out.println("\n----------------SHOW ALL PURCHASES---------------------------\n");
		
		showAllPurchases();
		
		System.out.println("\n--------------GET PURCHASE BY ID-----------------------------------\n");
		
		getPurchaseById(3);
		
		System.out.println("\n-----------UPDATE PURCHASE NAME------------------------\n");
		
		updatePurchaseName(3,"Wooden Stuff");
		
		System.out.println("\n----------DELETE A PURCHASE----------------------------------\n");
		
		deletePurchase(6);
		
		System.out.println("\n------------FINAL COUNT OF PURCHASES-------------------------\n");
		
		findCountOfPurchases();
		
		System.out.println("\n-------------------THE END---------------------------");
	}

	// READ
	// 1. Show all the data
	public void showAllPurchases() {
		 
		purchases = PurchaseRepository.findAll();
		
		purchases.forEach(purchase -> System.out.println(getPurchaseDetails(purchase)));
	}
	
	// 2. Get purchase by name
	public void getPurchaseById(Integer id) {
		System.out.println("Getting purchase by id: " + id);
		Purchase purchase = PurchaseRepository.findPurchaseById(id);
		System.out.println(getPurchaseDetails(purchase));
	}
	
	
	// 3. Get count of documents in the collection
	public void findCountOfPurchases() {
		long count = PurchaseRepository.count();
		System.out.println("Number of documents in the collection = " + count);
	}
	
	// UPDATE APPROACH 2: Using MongoTemplate
	public void updatePurchaseName(Integer id, String newName) {
		System.out.println("Updating name for purchase with id" + id);
		CustomRepository.updatePurchaseName(id, newName);
	}
	
	// DELETE
	public void deletePurchase(Integer id) {
		PurchaseRepository.deleteById(id);
		System.out.println("Purchase with id " + id + " deleted...");
	}
	// Print details in readable form
	
	public String getPurchaseDetails(Purchase purchase) {

		System.out.println(
		"Purchase: " + purchase.getPurchase()
		);
		
		return "";
	}


}


