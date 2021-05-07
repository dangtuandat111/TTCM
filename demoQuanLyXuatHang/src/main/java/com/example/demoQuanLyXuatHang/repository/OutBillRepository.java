package com.example.demoQuanLyXuatHang.repository;

import com.example.demoQuanLyXuatHang.entity.DisplayOutBill;
import com.example.demoQuanLyXuatHang.entity.OutBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutBillRepository extends JpaRepository<OutBill,Integer> {
    @Query("select new com.example.demoQuanLyXuatHang.entity.DisplayOutBill(o.id,l.id,l.productName,l.idCategory," +
            "l.detail,l.imageProduct,l.unit,d.amount,o.idBranch,o.billStatus,o.total,o.createTime,b.branchName,b.branchLocation) " +
            "from OutBill o , BRANCH b ," +
            "ListProduct l ,DetailOutbill d" +
            " where o.id=d.outBill.id and o.idBranch=b.id and d.listProduct.id=l.id" )
    List<DisplayOutBill> findAllbyMe ();

}
