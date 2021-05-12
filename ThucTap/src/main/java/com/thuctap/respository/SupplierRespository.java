package com.thuctap.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuctap.entity.supplier;

public interface SupplierRespository extends JpaRepository<supplier, Integer> {
	
}
