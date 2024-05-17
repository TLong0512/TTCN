package com.team12.fantafilm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Phim")
public class Phim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @Column
    private String detail;

    public Phim() {
    }

    public Phim(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }

    public Phim(String name, Long id, String detail) {
        this.name = name;
        this.id = id;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
