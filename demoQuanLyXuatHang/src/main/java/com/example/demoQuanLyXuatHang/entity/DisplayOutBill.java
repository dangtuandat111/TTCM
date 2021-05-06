package com.example.demoQuanLyXuatHang.entity;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class DisplayOutBill {
    public int idOutBill ;
    public int idProduct ;
    public int amount ;
    public  int total ;
    public  int idBranch ;
    public  String branchName;
    public  String branchLocation;
    public  String billStatus ;
    public Date createTime ;


    public DisplayOutBill() {
    }
}
