package com.team12.fantafilm.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private  String code;
    @Column(nullable = false)
    private  String name;
    @Column(nullable = false)
    private LocalDateTime start;

}
