package com.example.demoQuanLyXuatHang.repository;

import com.example.demoQuanLyXuatHang.entity.DisplayOutBill;
import com.example.demoQuanLyXuatHang.entity.OutBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutBillRepository extends JpaRepository<OutBill,Integer> {
    @Query("select new com.example.demoQuanLyXuatHang.entity.DisplayOutBill(o.id,d.product.id,d.amount,o.total,o.idBranch,o.billStatus,o.createTime) " +
            "from OutBill o , " +
            "DetailOutbill d" +
            " where o.id=d.outBill.id")
    List<DisplayOutBill> findAllbyMe ();
}
