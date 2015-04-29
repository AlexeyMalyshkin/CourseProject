package com.malyshkin.service.impl;

import com.malyshkin.entity.FamilyInvite;
import com.malyshkin.entity.User;
import com.malyshkin.repository.specification.FamilyInviteRepository;
import com.malyshkin.service.FamilyInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    return familyInviteRepository.findFamilyIniviteByUser(user);
  }

  @Override
  public List<FamilyInvite> findFamilyInvites(User user)
  {
    return familyInviteRepository.findFamilyInvitesByUser(user);
  }

  @Override
  public void save(FamilyInvite familyInvite)
  {
    familyInviteRepository.save(familyInvite);
  }
}
