package com.thuctap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuctap.dto.ProductDTO;
import com.thuctap.entity.category;
import com.thuctap.entity.product;
import com.thuctap.entity.product_detail;
import com.thuctap.service.FileStorageService;
import com.thuctap.service.IProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private FileStorageService fileStorageService;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@GetMapping("products")
	public ResponseEntity<List<product>> getAllProducts() {
		return productService.findAllProducts();
	}

	@GetMapping("categories")
	public ResponseEntity<List<category>> getAllCategories() {
		return productService.findAllCategories();
	}
	
	@GetMapping("products/{id}")
	public ResponseEntity<List<product_detail>> getProductDetaislById(@PathVariable("id") int id) {
		return productService.findProductDetailsById(id);
	}

	@PostMapping(value = "products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<product> createProduct(@RequestParam(value = "sendData") String sendData,
			@RequestParam(value = "image") MultipartFile file)
			throws JsonParseException, JsonMappingException, IOException {
		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/downloadFile/")
				.path(fileName).toUriString();
		ProductDTO product = objectMapper.readValue(sendData, ProductDTO.class);
		return productService.createProduct(product, fileDownloadUri);
	}
	
	@GetMapping(value = "/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						String.format("attachment; filename=\"%s\"", resource.getFilename()))
				.body(resource);
	}
}
