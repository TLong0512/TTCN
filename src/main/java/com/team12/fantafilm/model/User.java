package com.team12.fantafilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false)
    private  Boolean enabled;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private  String phoneNumber;
    @Column(nullable = true)
    private int point;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "rank_id")
    private RankCustomer rank;

}
