package com.example.demoQuanLyXuatHang.repository;

import com.example.demoQuanLyXuatHang.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
