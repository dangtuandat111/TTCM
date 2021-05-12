package com.thuctap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thuctap.entity.store;
import com.thuctap.service.IStoreService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class StoreController {
	@Autowired
	private IStoreService storeService;
	
	@GetMapping("stores")
	public ResponseEntity<List<store>> getAllStores() {
		return storeService.findAllStores();
	}
	
	@PostMapping("stores")
	public ResponseEntity<store> createStore(@RequestBody store store){
		return storeService.createStore(store);
	}
	
	@PutMapping("stores/{id}")
	public ResponseEntity<store> updateStore(@PathVariable("id") int id, @RequestBody store store){
		return storeService.updateStore(store, id);
	}
}
