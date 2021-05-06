package com.example.demoQuanLyXuatHang.repository;

import com.example.demoQuanLyXuatHang.entity.DetailOutbill;
import com.example.demoQuanLyXuatHang.entity.OutBill_ProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailOutBillRepository extends JpaRepository<DetailOutbill, OutBill_ProductKey> {
}
