package com.team12.fantafilm.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    public User() {
    }

    public User(Long id, String userName, String passWord, Boolean enabled, String fullName, Set<UserRole> userRoles) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.enabled = enabled;
        this.fullName = fullName;
        this.userRoles = userRoles;
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

    @Column
    private String userName;
    @Column
    private String passWord;
    @Column
    private  Boolean enabled;
    @Column
    private String fullName;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;


}
