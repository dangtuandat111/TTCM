package com.example.demoQuanLyXuatHang.repository;

import com.example.demoQuanLyXuatHang.entity.supplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<supplierEntity, Integer> {
    supplierEntity findBy(String id);
//	supplierEntity findById(Integer id);
}