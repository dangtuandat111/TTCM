package com.example.demoQuanLyXuatHang.repository;

import com.example.demoQuanLyXuatHang.entity.DisplayProduct;
import com.example.demoQuanLyXuatHang.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select new com.example.demoQuanLyXuatHang.entity.DisplayProduct(p.id,l.productName,l.detail,'stocking',l.imageProduct) from Product p , ListProduct l " +
            "where p.idList=l.id")
    List<DisplayProduct> findAllProduct ();
}
