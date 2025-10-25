package com.Atiq.Springproject.Entity;


import jakarta.persistence.*;

@Entity

@Table(name = "Customers")

public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int cell;

    private String address;

    public Customer() {
    }

    public Customer(int id, String name, int cell, String address) {
        this.id = id;
        this.name = name;
        this.cell = cell;
        this.address = address;
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

}
