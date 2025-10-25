package com.Atiq.Springproject.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.List;

@Entity

@Table(name = "Categories")


public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String categoryname;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    public Category() {
    }

    public Category(int id, String categoryname, List<Product> products) {
        this.id = id;
        this.categoryname = categoryname;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
