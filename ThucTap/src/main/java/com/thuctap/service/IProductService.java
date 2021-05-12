package com.thuctap.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.thuctap.dto.ProductDTO;
import com.thuctap.entity.category;
import com.thuctap.entity.product;
import com.thuctap.entity.product_detail;


public interface IProductService {
	ResponseEntity<List<product>> findAllProducts();
	
	ResponseEntity<List<product_detail>> findProductDetailsById(int id);
	
	ResponseEntity<List<category>> findAllCategories();
	
	ResponseEntity<product> createProduct(ProductDTO product, String fileDownloadUri);
}


