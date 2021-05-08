package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.supplierEntity;

public interface SupplierRepository extends JpaRepository<supplierEntity, Integer>{
	supplierEntity findBy(String id);
//	supplierEntity findById(Integer id);
}
