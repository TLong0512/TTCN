package com.team12.fantafilm.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public User(Long id, String userName, String passWord, Boolean enabled, String fullName, String code, String email, String phoneNumber, int point, Set<UserRole> userRoles, Rank rank) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.enabled = enabled;
        this.fullName = fullName;
        this.code = code;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.point = point;
        this.userRoles = userRoles;
        this.rank = rank;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private  String phoneNumber;
    @Column(nullable = true)
    private int point;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "rank_id",referencedColumnName = "id")
    private Rank rank;

}
