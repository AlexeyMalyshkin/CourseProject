package com.malyshkin.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

    private static final long serialVersionUID = -2888541738554144466L;

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = User.class, mappedBy = "role")
    private Set<User> users;

    public Role() {
    }

    public Role(long l, String string) {
        id = l;
        name = string;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}