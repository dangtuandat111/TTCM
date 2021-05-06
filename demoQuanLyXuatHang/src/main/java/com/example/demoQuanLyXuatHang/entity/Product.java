package com.example.demoQuanLyXuatHang.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private int amount ;
    private int idList ;
    @Temporal(TemporalType.DATE)
    private Date expiryDay;



    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<DetailOutbill> detailOutbills = new ArrayList<>();

    public Product(int amount, int idList) {
        this.amount = amount;
        this.idList = idList;
        expiryDay= new Date();
    }

    public Product() {
        expiryDay= new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public Date getExpiryDay() {
        return expiryDay;
    }

    public void setExpiryDay(Date expiryDay) {
        this.expiryDay = expiryDay;
    }
    public List<DetailOutbill> getDetailOutbills() {
        return detailOutbills;
    }

    public void setDetailOutbills(List<DetailOutbill> detailOutbills) {
        this.detailOutbills = detailOutbills;
    }
}
