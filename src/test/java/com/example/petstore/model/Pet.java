package com.example.petstore.model;

public class Pet {
    public long id;
    public String name;
    public String status;
    
    public Pet(long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
