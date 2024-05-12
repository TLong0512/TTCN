package com.team12.fantafilm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;
    @Column(name = "name")
    private  String categoryName;
    @Column(name = "status")
    private Boolean categoryStatus;

    public Category(Long id, String categoryName, Boolean categoryStatus) {
        Id = id;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public Category() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}
