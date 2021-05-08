package com.example.demoQuanLyXuatHang.repository;

import com.example.demoQuanLyXuatHang.entity.DisplayOutBill;
import com.example.demoQuanLyXuatHang.entity.ListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListProductRepository extends JpaRepository<ListProduct,Integer> {
    @Query(value = "select l from OutBill o , ListProduct l , DetailOutbill d where o.id = d.outBill.id and d.listProduct.id= l.id" +
            "and o.id = ?2 and l.id =?1",nativeQuery = true)
    ListProduct findListProductByIdAndIdOutBill(int id1 , int id2);
}
