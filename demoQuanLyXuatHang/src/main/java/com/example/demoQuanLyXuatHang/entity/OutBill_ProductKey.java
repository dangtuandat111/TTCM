package com.example.demoQuanLyXuatHang.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class OutBill_ProductKey implements Serializable {
    private int idListProduct;
    private int idOutBill;

    public OutBill_ProductKey() {
    }

    public int getIdListProduct() {
        return idListProduct;
    }

    public void setIdListProduct(int idProduct) {
        this.idListProduct = idProduct;
    }

    public int getIdOutBill() {
        return idOutBill;
    }

    public void setIdOutBill(int idOutBill) {
        this.idOutBill = idOutBill;
    }

    public OutBill_ProductKey(int idListProduct, int idOutBill) {
        this.idListProduct = idListProduct;
        this.idOutBill = idOutBill;
    }
}
