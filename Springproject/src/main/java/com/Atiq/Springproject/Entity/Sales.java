package com.Atiq.Springproject.Entity;


import jakarta.persistence.*;


import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Sales")



public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customername;
    private Date salesdate;
    private int totalprice;

    private int quantity;
    private float discount;



    @ManyToMany
    private List<Product> product;


    @ManyToOne
    private SalesDetails salesDetails;

    public Sales() {
    }

    public Sales(int id, String customername, Date salesdate,
                 int totalprice, int quantity, float discount,
                 List<Product> product, SalesDetails salesDetails) {
        this.id = id;
        this.customername = customername;
        this.salesdate = salesdate;
        this.totalprice = totalprice;
        this.quantity = quantity;
        this.discount = discount;
        this.product = product;
        this.salesDetails = salesDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Date getSalesdate() {
        return salesdate;
    }

    public void setSalesdate(Date salesdate) {
        this.salesdate = salesdate;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public SalesDetails getSalesDetails() {
        return salesDetails;
    }

    public void setSalesDetails(SalesDetails salesDetails) {
        this.salesDetails = salesDetails;
    }



}
