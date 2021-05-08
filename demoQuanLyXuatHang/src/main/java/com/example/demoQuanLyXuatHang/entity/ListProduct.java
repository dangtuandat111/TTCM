package com.example.demoQuanLyXuatHang.entity;

import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="List_Product")
@ToString
public class ListProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String productName;
    @Temporal(TemporalType.DATE)
    private Date expiry;
    private int idCategory;
    private String detail;
    private String imageProduct;
    private String unit ;

    @Transient
    private List<Integer> products= new ArrayList<>();


    @OneToMany(mappedBy = "listProduct",cascade = CascadeType.ALL)
    private List<DetailOutbill> detailOutbills = new ArrayList<>();


    public List<Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }

    public ListProduct(String productName, int idCategory, String detail, String imageProduct, String unit) {
        this.productName = productName;
        this.idCategory = idCategory;
        this.detail = detail;
        this.imageProduct = imageProduct;
        this.unit = unit;
        expiry = new Date();
    }

    public ListProduct() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String prductName) {
        this.productName = prductName;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<DetailOutbill> getDetailOutbills() {
        return detailOutbills;
    }

    public void setDetailOutbills(List<DetailOutbill> detailOutbills) {
        this.detailOutbills = detailOutbills;
    }
}
