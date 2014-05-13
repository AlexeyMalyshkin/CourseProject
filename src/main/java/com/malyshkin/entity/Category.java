package com.malyshkin.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private long id;

    private String name;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Transaction.class, mappedBy = "category")
    private Set<Transaction> transactions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", transactions=" + transactions +
                '}';
    }
}
