package TTCM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import DTO.SupplierDTO;
import Entity.supplierEntity;
import antlr.collections.List;
import repository.SupplierRepository;

@CrossOrigin
@Controller
public class NewSupplier {

	@Autowired
	SupplierRepository supplierRepository;
	
	@GetMapping("/supplier/getAll")
    @ResponseBody
    public List getHome(){
        return (List) supplierRepository.findAll();
    }

	@PostMapping(value = "/supplier/add")
	@ResponseBody
	public String createSupplier(@RequestParam String name,@RequestParam String location,
			@RequestParam String phone) {
		try {
			supplierEntity supplier = new supplierEntity();
			supplier.setSupplier_name(name);
			supplier.setSupplier_location(location);
			supplier.setPhone(phone);
			
			supplierRepository.save(supplier);
		}catch(Exception Ex) {
			return Ex.getMessage();
		}
		
		return "Thanh cong";
	}

	@PutMapping(value = "/supplier/update/{id}	")
	@ResponseBody
	public String updateSupplier(@PathVariable(name = "id") int id,
			@PathVariable(name = "name") String name ,
			@PathVariable(name = "location") String location ,
			@PathVariable(name = "phone") String phone) {
		
		try {
			supplierEntity supplier = new supplierEntity();
			supplier = supplierRepository.findById(id).orElse(null);
			supplier.setSupplier_name(name);
			supplier.setSupplier_location(location);
			supplier.setPhone(phone);
			
			supplierRepository.save(supplier);
		}catch(Exception Ex) {
			return Ex.getMessage();
		}
		
		return "Thanh cong";
	}

	@DeleteMapping(value = "/supplier/delete/{id}")
	@ResponseBody
	public String deleteSupplier(@PathVariable(name = "id") int id) {
		try {
			supplierRepository.deleteById(id);
	        return "Thanh cong";
		}catch(Exception Ex) {
			return Ex.getMessage();
		}
		
	}

}
