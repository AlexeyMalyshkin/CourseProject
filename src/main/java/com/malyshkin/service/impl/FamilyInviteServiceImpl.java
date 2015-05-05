package com.malyshkin.service.impl;

import com.malyshkin.entity.FamilyInvite;
import com.malyshkin.entity.User;
import com.malyshkin.repository.specification.FamilyInviteRepository;
import com.malyshkin.service.FamilyInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FamilyInviteServiceImpl implements FamilyInviteService
{
  @Autowired
  private FamilyInviteRepository familyInviteRepository;


  @Override
  public FamilyInvite findFamilyInvite(User user)
  {
    return familyInviteRepository.findFamilyIniviteByFrom(user);
  }

  // rename
  @Override
  public List<FamilyInvite> findFamilyInvitesByTo(User user)
  {

    List<FamilyInvite> invites = familyInviteRepository.findFamilyInvitesByTo(user);
    List<FamilyInvite> result = new ArrayList<>();

    for(FamilyInvite invite : invites){
      if(!invite.isAccepted()){
        result.add(invite);
      }
    }

    return result;
  }

  @Override
  public void save(FamilyInvite familyInvite)
  {
    familyInviteRepository.save(familyInvite);
  }

  @Override
  public FamilyInvite findFamilyInvite(long familyInviteId)
  {
    return familyInviteRepository.findOne(familyInviteId);
  }
}
