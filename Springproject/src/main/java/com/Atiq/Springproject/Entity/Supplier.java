package com.Atiq.Springproject.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.List;

@Entity

@Table(name = "Suppliers")

public class Supplier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private int cell;
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> products;

    public Supplier() {
    }

    public Supplier(int id, String name, String email,
                    int cell, String address, List<Product> products) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cell = cell;
        this.address = address;
        this.products = products;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
