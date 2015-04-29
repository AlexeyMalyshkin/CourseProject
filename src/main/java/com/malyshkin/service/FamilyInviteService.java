package com.malyshkin.service;

import com.malyshkin.entity.FamilyInvite;
import com.malyshkin.entity.User;

import java.util.List;

public interface FamilyInviteService
{
  public FamilyInvite findFamilyInvite(User user);

  public List<FamilyInvite> findFamilyInvites(User user);

  public void save(FamilyInvite familyInvite);
}
