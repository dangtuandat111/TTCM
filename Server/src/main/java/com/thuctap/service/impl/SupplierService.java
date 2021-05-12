package com.thuctap.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thuctap.entity.supplier;
import com.thuctap.respository.SupplierRespository;
import com.thuctap.service.ISupplierService;

@Service
public class SupplierService implements ISupplierService {
	@Autowired
	private SupplierRespository respository;
	
	@Override
	public ResponseEntity<List<supplier>> fistorendAllSuppliers() {
		List<supplier> list = respository.findAll();
		return new ResponseEntity<List<supplier>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<supplier> createSupplier(supplier supplier) {
		return new ResponseEntity<supplier>(respository.save(supplier), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<supplier> updateSupplier(supplier supplier, int id) {
		Optional<supplier> oldSupplier = respository.findById(id);

		if (oldSupplier.isPresent()) {
			supplier _supplier = oldSupplier.get();
			_supplier.setName(supplier.getName());
			_supplier.setLocation(supplier.getLocation());
			_supplier.setPhoneNumber(supplier.getPhoneNumber());
			_supplier.setStatus(supplier.getStatus());
			return new ResponseEntity<>(respository.save(_supplier), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
