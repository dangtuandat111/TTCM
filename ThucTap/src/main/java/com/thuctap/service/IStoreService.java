package com.thuctap.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.thuctap.entity.store;

public interface IStoreService {
	ResponseEntity<List<store>> findAllStores();
	
	ResponseEntity<store> createStore(store store);
	
	ResponseEntity<store> updateStore(store store, int id);
}
