package com.example.demoQuanLyXuatHang.repository;

import com.example.demoQuanLyXuatHang.entity.supplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<supplierEntity,Integer> {
}
