package com.malyshkin.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Family implements Serializable
{
  private static final long serialVersionUID = 1480589175037912639L;

  @Id
  @GeneratedValue
  @Column(unique = true, nullable = false)
  private long id;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = User.class, mappedBy = "family")
  private List<User> users;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = FamilyInvite.class, mappedBy = "family")
  private List<FamilyInvite> familyInvites;

  public long getId()
  {
    return id;
  }

  public void setId(long id)
  {
    this.id = id;
  }

  public List<User> getUsers()
  {
    return users;
  }

  public void setUsers(List<User> users)
  {
    this.users = users;
  }

  public List<FamilyInvite> getFamilyInvites()
  {
    return familyInvites;
  }

  public void setFamilyInvites(List<FamilyInvite> familyInvites)
  {
    this.familyInvites = familyInvites;
  }

  @Override
  public String toString()
  {
    return "Family{" +
      "id=" + id +
      ", users=" + users +
      '}';
  }
}
