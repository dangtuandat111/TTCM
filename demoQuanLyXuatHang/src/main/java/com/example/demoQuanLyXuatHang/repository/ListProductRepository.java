package com.example.demoQuanLyXuatHang.repository;

import com.example.demoQuanLyXuatHang.entity.ListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListProductRepository extends JpaRepository<ListProduct,Integer> {
}
