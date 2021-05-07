package com.example.demoQuanLyXuatHang.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class OutBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private int idBranch;
    private String billStatus;
    private int total ;
    @Temporal(TemporalType.DATE)
    private Date createTime ;

    public List<DetailOutbill> getDetailOutbills() {
        return detailOutbills;
    }

    public void setDetailOutbills(List<DetailOutbill> detailOutbills) {
        this.detailOutbills = detailOutbills;
    }

    @OneToMany(mappedBy = "outBill",cascade = CascadeType.ALL)
    private List<DetailOutbill> detailOutbills = new ArrayList<>();



    public OutBill(int idBranch, String billStatus, int total) {
        this.idBranch = idBranch;
        this.billStatus = billStatus;
        this.total = total;
        createTime= new Date();
    }

    public OutBill() {
        createTime= new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
