package com.example.demoQuanLyXuatHang.entity;

import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@ToString
public class DetailOutbill {
    @EmbeddedId
    private OutBill_ProductKey id ;

    @ManyToOne
    @MapsId("idListProduct")
    @JoinColumn(name = "id_ListProduct")
    private ListProduct listProduct;

    public DetailOutbill(int amount) {
        this.amount = amount;
    }

    @ManyToOne
    @MapsId("idOutBill")
    @JoinColumn(name = "id_OutBill")
    private OutBill outBill;

    private int amount ;



    public DetailOutbill() {
    }

    public OutBill_ProductKey getId() {
        return id;
    }

    public void setId(OutBill_ProductKey id) {
        this.id = id;
    }

    public ListProduct getListProduct() {
        return listProduct;
    }

    public void setListProduct(ListProduct listProduct) {
        this.listProduct = listProduct;
    }

    public OutBill getOutBill() {
        return outBill;
    }

    public void setOutBill(OutBill outBill) {
        this.outBill = outBill;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
