package com.mongo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

	}

	@Override
	public void run(String... args) throws Exception {
		
		// Clean up documents within the collection 'purchases'
		PurchaseRepository.deleteAll();
		
		System.out.println("-------------CREATE PURCHASES-------------------------------\n");
		
		createPurchases();

		System.out.println("\n----------------SHOW ALL PURCHASES---------------------------\n");
		
		showAllPurchases();
		
		System.out.println("\n--------------GET PURCHASE BY ID-----------------------------------\n");
		
		getPurchaseById(3);
		
		System.out.println("\n-----------UPDATE PURCHASE NAME------------------------\n");
		
		updatePurchaseName(3,"maintenance tools");
		
		System.out.println("\n----------DELETE A PURCHASE----------------------------------\n");
		
		deletePurchase(6);
		
		System.out.println("\n------------FINAL COUNT OF PURCHASES-------------------------\n");
		
		findCountOfPurchases();
		
		System.out.println("\n-------------------THE END---------------------------");
	}

	
	//CREATE
	void createPurchases() {
		System.out.println("Data creation started...");

		PurchaseRepository.save(new Purchase(0,"clothes"));
		PurchaseRepository.save(new Purchase(1,"electronics"));
		PurchaseRepository.save(new Purchase(2,"toys"));
		PurchaseRepository.save(new Purchase(3,"furniture"));
		PurchaseRepository.save(new Purchase(4,"sports equipement"));
		PurchaseRepository.save(new Purchase(5,"stationery"));
		PurchaseRepository.save(new Purchase(6,"books"));
		PurchaseRepository.save(new Purchase(7,"groceries"));
		PurchaseRepository.save(new Purchase(8,"accessories"));
		PurchaseRepository.save(new Purchase(9,"fast-food"));
		PurchaseRepository.save(new Purchase(9,"gadgets"));
	
		System.out.println("Data creation complete...");
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
		System.out.println("Updating name for purchase with id:" + id);
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


