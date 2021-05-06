package com.example.demoQuanLyXuatHang.entity;

import javax.persistence.*;

@Entity
@Table
public class DetailOutbill {
    @EmbeddedId
    private OutBill_ProductKey id ;

    @ManyToOne
    @MapsId("idProduct")
    @JoinColumn(name = "id_Product")
    private Product product;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
