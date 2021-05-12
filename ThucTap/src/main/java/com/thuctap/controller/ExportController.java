package com.thuctap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thuctap.dto.ExportDTO;
import com.thuctap.entity.export_bill;
import com.thuctap.entity.export_bill_detail;
import com.thuctap.service.IExportService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class ExportController {
	@Autowired
	private IExportService exportService;

	@GetMapping("exports")
	public ResponseEntity<List<export_bill>> getAllExportBills() {
		return exportService.findAllExport_Bills();
	}
	
	@GetMapping("exports/{id}")
	public ResponseEntity<List<export_bill_detail>> getExportBillsDetailById(@PathVariable("id") int id) {
		return exportService.findExportBillDetailsById(id);
	}
	
	@PostMapping("exports")
	public ResponseEntity<String> createExportBill(@RequestBody ExportDTO exportBill){
		export_bill bill =  exportService.createBill(exportBill.getStore());
		exportService.createBillDetails(exportBill.getDetails(),bill);
		return new ResponseEntity<String>("Created", HttpStatus.CREATED);
	}
}
