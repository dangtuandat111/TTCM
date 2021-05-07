package com.example.demoQuanLyXuatHang.entity;

import lombok.AllArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@AllArgsConstructor
public class DisplayOutBill {
    public int idOutBill ;
    public int idListProduct ;
    public String productName;
    public int idCategory;
    public String detail;
    public String imageProduct;
    public String unit ;
    public int amount ;
    public int idBranch;
    public String billStatus;
    public int total ;
    public Date createTime ;
    public String branchName;
    public String branchLocation;


    public DisplayOutBill() {
    }
}
