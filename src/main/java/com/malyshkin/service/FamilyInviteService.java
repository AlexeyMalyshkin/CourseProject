package com.malyshkin.service;

import com.malyshkin.entity.FamilyInvite;
import com.malyshkin.entity.User;

import java.util.List;

public interface FamilyInviteService
{
  public FamilyInvite findFamilyInvite(User user);

  public List<FamilyInvite> findFamilyInvitesByTo(User user);

  public void save(FamilyInvite familyInvite);

  public FamilyInvite findFamilyInvite(long familyInviteId);

  public void delete(FamilyInvite familyInvite);
}
