package com.Atiq.Springproject.Entity;


import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String name;

    private String photo;

    private int stock;

    private int quantity;

    private int unitprice;
//    private float discount;

    private Date manufactureDate;

    private Date expiryDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branchId")
    private Branch branch;



    public Product() {
    }

    public Product(int id, Branch branch, String name, String photo, int stock,
                   int quantity, int unitprice, Date manufactureDate, Date expiryDate,
                   Category category, Supplier supplier) {
        this.id = id;
        this.branch = branch;
        this.name = name;
        this.photo = photo;
        this.stock = stock;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.category = category;
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }



}
