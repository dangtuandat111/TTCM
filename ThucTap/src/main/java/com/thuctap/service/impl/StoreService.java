package com.thuctap.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thuctap.entity.store;
import com.thuctap.respository.StoreRespository;
import com.thuctap.service.IStoreService;

@Service
public class StoreService implements IStoreService {
	@Autowired
	private StoreRespository respository;

	@Override
	public ResponseEntity<List<store>> findAllStores() {
		List<store> list = respository.findAll();
		return new ResponseEntity<List<store>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<store> createStore(store store) {
		return new ResponseEntity<store>(respository.save(store), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<store> updateStore(store store, int id) {
		Optional<store> oldStore = respository.findById(id);

		if (oldStore.isPresent()) {
			store _store = oldStore.get();
			_store.setName(store.getName());
			_store.setLocation(store.getLocation());
			_store.setPhoneNumber(store.getPhoneNumber());
			_store.setStatus(store.getStatus());
			return new ResponseEntity<>(respository.save(_store), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
