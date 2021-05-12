package com.thuctap.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thuctap.dto.ExportDetailDTO;
import com.thuctap.entity.detail_key;
import com.thuctap.entity.export_bill;
import com.thuctap.entity.export_bill_detail;
import com.thuctap.entity.product;
import com.thuctap.entity.product_detail;
import com.thuctap.entity.store;
import com.thuctap.respository.ExportBillDetailRespository;
import com.thuctap.respository.ExportBillRespository;
import com.thuctap.respository.ProductDetailRespository;
import com.thuctap.respository.ProductRespository;
import com.thuctap.respository.StoreRespository;
import com.thuctap.service.IExportService;

@Service
public class ExportService implements IExportService {
	@Autowired
	private ExportBillRespository respository;

	@Autowired
	private StoreRespository storeRespository;
	
	@Autowired
	private ProductRespository productRespository;
	
	@Autowired
	private ExportBillDetailRespository billDetailRespository;
	
	@Autowired
	private ProductDetailRespository productDetailRespository;

	@Override
	public ResponseEntity<List<export_bill>> findAllExport_Bills() {
		List<export_bill> list = respository.findAll();
		return new ResponseEntity<List<export_bill>>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<export_bill_detail>> findExportBillDetailsById(int id) {
		Optional<export_bill> bill = respository.findById(id);
		return new ResponseEntity<List<export_bill_detail>>(bill.get().getExport_bill_details(), HttpStatus.OK);
	}

	@Override
	public export_bill createBill(int store_id) {
		export_bill export_bill = new export_bill();
		Optional<store> store = storeRespository.findById(store_id);
		export_bill.setStore(store.get());
		export_bill.setStatus("done");
		export_bill.setCreated_date( new Date());
		respository.save(export_bill);
		return respository.save(export_bill);
	}

	@Override
	public ResponseEntity<export_bill> createBillDetails(ExportDetailDTO[] exportDetails, export_bill export_bill) {
		float s = 0;
		for(int i = 0; i < exportDetails.length; i++) {
			Optional<product> product = productRespository.findById(exportDetails[i].getId());
			detail_key id = new detail_key();
			id.setBillId(export_bill.getId());
			id.setProductId(exportDetails[i].getId());
			
			export_bill_detail detail = new export_bill_detail();
			detail.setCost(product.get().getProduct_detail().get(0).getCost());
			detail.setProduct(product.get());
			detail.setQuantity(exportDetails[i].getQuantity());
			detail.setExport_bill(export_bill);
			detail.setId(id);
			billDetailRespository.save(detail);
			int currentQuantity = product.get().getProduct_detail().get(0).getQuantity();
			
			product_detail _product_detail = product.get().getProduct_detail().get(0);
			_product_detail.setQuantity(currentQuantity - exportDetails[i].getQuantity());
			productDetailRespository.save(_product_detail);
			
			s += exportDetails[i].getQuantity() * product.get().getProduct_detail().get(0).getCost();
			
		}
		export_bill bill= export_bill;
		bill.setTotal(s);
		respository.save(bill);
		return null;
	}

}
