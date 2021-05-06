package com.example.demoQuanLyXuatHang.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OutBill_ProductKey implements Serializable {
    private int idProduct ;
    private int idOutBill;

    public OutBill_ProductKey() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdOutBill() {
        return idOutBill;
    }

    public void setIdOutBill(int idOutBill) {
        this.idOutBill = idOutBill;
    }

    public OutBill_ProductKey(int idProduct, int idOutBill) {
        this.idProduct = idProduct;
        this.idOutBill = idOutBill;
    }
}
