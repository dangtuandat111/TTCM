package com.example.demoQuanLyXuatHang.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Branch {
    @Id
    private int id ;
    private String branchName;
    private String branchLocation;


    public Branch(String branchName, String branchLocation) {
        this.branchName = branchName;
        this.branchLocation = branchLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }



    public Branch() {
    }
}
