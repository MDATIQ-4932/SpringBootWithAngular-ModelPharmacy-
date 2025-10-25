package com.Atiq.Springproject.Entity;


import jakarta.persistence.*;


@Entity
@Table(name = "SalesDetails")

public class SalesDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sales_id", nullable = false)
    private Sales sale; // Reference back to Sales

//    @ManyToOne
//    @JoinColumn(name = "sales_bonani_id", nullable = false)
//    private BonaniBranceSales bonaniBranceSales; // Reference back to Sales

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;

    private float unitPrice;
    private float totalPrice;
    private float discount;

    public SalesDetails() {
    }

    public SalesDetails(int id, Sales sale, Product product, int quantity, float unitPrice, float totalPrice, float discount) {
        this.id = id;
        this.sale = sale;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sales getSale() {
        return sale;
    }

    public void setSale(Sales sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }


}
