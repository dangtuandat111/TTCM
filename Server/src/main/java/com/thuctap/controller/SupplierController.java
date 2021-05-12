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

import com.thuctap.entity.supplier;
import com.thuctap.service.ISupplierService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class SupplierController {
	
	@Autowired
	private ISupplierService supplierService;
	
	@GetMapping("suppliers")
	public ResponseEntity<List<supplier>> getAllSuppliers() {
		return supplierService.fistorendAllSuppliers();
	}
	
	@PostMapping("suppliers")
	public ResponseEntity<supplier> createSupplier(@RequestBody supplier supplier){
		return supplierService.createSupplier(supplier);
	}
	
	@PutMapping("suppliers/{id}")
	public ResponseEntity<supplier> updateSupplier(@PathVariable("id") int id, @RequestBody supplier supplier){
		return supplierService.updateSupplier(supplier, id);
	}
	
}
