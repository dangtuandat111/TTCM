package com.thuctap.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuctap.entity.product_detail;

public interface ProductDetailRespository extends JpaRepository<product_detail, Integer> {

}
