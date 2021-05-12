package com.thuctap.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuctap.entity.product;

public interface ProductRespository extends JpaRepository<product, Integer> {

}
