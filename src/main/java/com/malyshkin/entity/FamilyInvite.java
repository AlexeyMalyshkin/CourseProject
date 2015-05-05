package com.malyshkin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class FamilyInvite implements Serializable
{
  private static final long serialVersionUID = 1480589175037912632L;

  @Id
  @GeneratedValue
  @Column(unique = true, nullable = false)
  private long id;

  @ManyToOne
  private User from;

  @ManyToOne
  private User to;

  @ManyToOne
  private Family family;

  private boolean accepted;

  public static long getSerialVersionUID()
  {
    return serialVersionUID;
  }

  public long getId()
  {
    return id;
  }

  public void setId(long id)
  {
    this.id = id;
  }

  public Family getFamily()
  {
    return family;
  }

  public void setFamily(Family family)
  {
    this.family = family;
  }

  public boolean isAccepted()
  {
    return accepted;
  }

  public void setAccepted(boolean accepted)
  {
    this.accepted = accepted;
  }

  public User getFrom()
  {
    return from;
  }

  public void setFrom(User from)
  {
    this.from = from;
  }

  public User getTo()
  {
    return to;
  }

  public void setTo(User to)
  {
    this.to = to;
  }
}
