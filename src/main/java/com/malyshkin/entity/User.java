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
import java.util.List;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1480589175037914639L;

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private long id;

    private String email;

    private String password;

    private boolean familyAdmin;

    @ManyToOne
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Category.class, mappedBy = "user")
    private List<Category> categories;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = FamilyInvite.class, mappedBy = "to")
    private List<FamilyInvite> familyInvitesRecieved;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = FamilyInvite.class, mappedBy = "from")
    private List<FamilyInvite> familyInvitesSent;

    @ManyToOne(cascade =  CascadeType.ALL)
    private Family family;

    public User() {
    }

    public User(long id, String login, String password, String email,
           Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object obj)
    {
        return id == ((User)obj).getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Family getFamily()
    {
        return family;
    }

    public void setFamily(Family family)
    {
        this.family = family;
    }

    public List<FamilyInvite> getFamilyInvitesSent()
    {
        return familyInvitesSent;
    }

    public void setFamilyInvitesSent(List<FamilyInvite> familyInvitesSent)
    {
        this.familyInvitesSent = familyInvitesSent;
    }

    public List<FamilyInvite> getFamilyInvitesRecieved()
    {
        return familyInvitesRecieved;
    }

    public void setFamilyInvitesRecieved(List<FamilyInvite> familyInvitesRecieved)
    {
        this.familyInvitesRecieved = familyInvitesRecieved;
    }

    public boolean isFamilyAdmin()
    {
        return familyAdmin;
    }

    public void setFamilyAdmin(boolean familyAdmin)
    {
        this.familyAdmin = familyAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", categories=" + categories +
                '}';
    }
}
