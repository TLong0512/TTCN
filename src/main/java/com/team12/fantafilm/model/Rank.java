package com.team12.fantafilm.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rank_customer")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int point;
    @Column
    private Boolean status;

    @OneToMany(mappedBy = "rank", fetch = FetchType.EAGER)
    List<User> users = new ArrayList<>();

    public Rank() {

    }

    public List<User> getUsers() {
        return users;
    }


    public Rank(Long id, String name, String description, int point, Boolean status, List<User> users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.point = point;
        this.status = status;
        this.users = users;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}


