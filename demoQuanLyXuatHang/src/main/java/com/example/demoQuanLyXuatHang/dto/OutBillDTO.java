package com.example.demoQuanLyXuatHang.dto;

public class OutBillDTO {
    private ProductDTO[] products;
    private String store;
    private String status;
    public ProductDTO[] getProducts() {
        return products;
    }

    public void setProducts(ProductDTO[] products) {
        this.products = products;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
