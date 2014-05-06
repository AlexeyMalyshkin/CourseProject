package com.malyshkin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1480589175037914639L;

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "ROLE")
    private Role role;

    @Transient
    private String passVerify;

//    @Transient
//    private boolean conditionTrue;

    public void setPassVerify(String passVerify) {
        this.passVerify = passVerify;
    }

    public String getPassVerify() {
        return passVerify;
    }

    public User() {
    }

    public User(long id, String login, String password, String email,
           Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

//    public static int getDiffYears(Date last) {
//        Date first = new Date();
//        Calendar birth = Calendar.getInstance();
//        birth.setTime(last);
//        Calendar curr = Calendar.getInstance();
//        curr.setTime(first);
//        int diff = curr.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
//        if (birth.get(Calendar.MONTH) > curr.get(Calendar.MONTH)
//                || (birth.get(Calendar.MONTH) == curr.get(Calendar.MONTH) && birth
//                        .get(Calendar.DATE) > curr.get(Calendar.DATE))) {
//            diff--;
//        }
//        return diff;
//    }

    @Override
    public String toString() {
        return "\nUser [id=" + id + ", login=" + login + ", password="
                + password + ", email=" + email
                + ", role=" + role + "]";
    }

}
