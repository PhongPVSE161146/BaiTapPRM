package com.example.fragmentproducttest;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String description;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}

