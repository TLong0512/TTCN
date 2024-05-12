package com.team12.fantafilm.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column
    private String name;

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


    public Role(Long id, String name, Set<UserRole> ruleUsers) {
        this.id = id;
        this.name = name;
        this.ruleUsers = ruleUsers;
    }

    public void setRuleUsers(Set<UserRole> ruleUsers) {
        this.ruleUsers = ruleUsers;
    }

    public Set<UserRole> getRuleUsers() {
        return ruleUsers;
    }

    public Role() {
    }

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private Set<UserRole> ruleUsers;
}
