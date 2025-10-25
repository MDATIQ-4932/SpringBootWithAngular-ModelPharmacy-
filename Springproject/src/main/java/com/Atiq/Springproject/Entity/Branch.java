package com.Atiq.Springproject.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.List;

@Entity

@Table(name = "Branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String branchName;
    private String location;


    @JsonIgnore
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Product> products;

    public Branch() {
    }

    public Branch(int id, String branchName, String location, List<Product> products) {
        this.id = id;
        this.branchName = branchName;
        this.location = location;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
