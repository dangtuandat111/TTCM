package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DTO.SupplierDTO;
import Entity.supplierEntity;
import repository.SupplierRepository;

@Service
public class SupplierService{
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	//@Override
	public String save(SupplierDTO suppDTO) {
		supplierEntity supplier = new supplierEntity();
		supplier.setSupplier_name(suppDTO.getSupplier_name());
		supplier.setSupplier_location(suppDTO.getSupplier_location());
		supplier.setPhone(suppDTO.getPhone());
		
		supplierRepository.save(supplier);
		return "redirect:/";
	}

}
