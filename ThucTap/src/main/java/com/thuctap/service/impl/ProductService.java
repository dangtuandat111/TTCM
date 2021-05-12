package com.thuctap.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thuctap.dto.ProductDTO;
import com.thuctap.entity.category;
import com.thuctap.entity.product;
import com.thuctap.entity.product_detail;
import com.thuctap.respository.CategoryRespository;
import com.thuctap.respository.ProductRespository;
import com.thuctap.service.IProductService;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ProductRespository productRespository;
	
	@Autowired
	private CategoryRespository categoryRespository;

	@Override
	public ResponseEntity<List<product>> findAllProducts() {
		List<product> list = productRespository.findAll();
		return new ResponseEntity<List<product>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<category>> findAllCategories() {
		List<category> list = categoryRespository.findAll();
		return new ResponseEntity<List<category>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<product> createProduct(ProductDTO product, String fileDownloadUri) {
		Optional<category> category = categoryRespository.findById(product.getCategory_id());
		product newProduct = new product(product.getName(), product.getStatus(),
										fileDownloadUri, product.getDescription(), category.get());
		return new ResponseEntity<product>(productRespository.save(newProduct), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<product_detail>> findProductDetailsById(int id) {
		Optional<product> product = productRespository.findById(id);
		return new ResponseEntity<List<product_detail>>(product.get().getProduct_detail(), HttpStatus.OK);
	}

}
