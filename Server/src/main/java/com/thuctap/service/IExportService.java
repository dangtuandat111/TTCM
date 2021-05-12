package com.thuctap.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.thuctap.dto.ExportDetailDTO;
import com.thuctap.entity.export_bill;
import com.thuctap.entity.export_bill_detail;

public interface IExportService {
	ResponseEntity<List<export_bill>> findAllExport_Bills();
	
	ResponseEntity<List<export_bill_detail>> findExportBillDetailsById(int id);
	
	export_bill createBill(int store_id);
	
	ResponseEntity<export_bill> createBillDetails(ExportDetailDTO[] exportDetails, export_bill export_bill);
}
