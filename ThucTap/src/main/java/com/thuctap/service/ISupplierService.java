package com.thuctap.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.thuctap.entity.supplier;

public interface ISupplierService {
	
	ResponseEntity<List<supplier>> fistorendAllSuppliers();
	
	ResponseEntity<supplier> createSupplier(supplier supplier);
	
	ResponseEntity<supplier> updateSupplier(supplier supplier, int id);
}
