package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;

import java.util.Date;

public class Product {
    private String productId;
    private String name;
    private Date dateSupplied;
    private int quantityInStock;
    private double unitPrice;

    // Constructors
    public Product() {
        // Default constructor
    }

    public Product(String productId, String name, Date dateSupplied, int quantityInStock, double unitPrice) {
        this.productId = productId;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", dateSupplied=" + dateSupplied +
                ", quantityInStock=" + quantityInStock +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public String toJOSONString(){
        return String.format("{\"productId\":%s, \"name\":%s, \"dateSupplied\":%s, \"quantityInStock\":%d, \"unitPrice\":%,.2f}",productId,name,dateSupplied,quantityInStock,unitPrice);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateSupplied() {
        return dateSupplied;
    }

    public void setDateSupplied(Date dateSupplied) {
        this.dateSupplied = dateSupplied;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
