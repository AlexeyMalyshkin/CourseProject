package com.malyshkin.service.impl;

import com.malyshkin.entity.Family;
import com.malyshkin.repository.FamilyRepository;
import com.malyshkin.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FamilyServiceImpl implements FamilyService
{
  @Autowired
  private FamilyRepository familyRepository;

  @Override
  public Family findFamily(long id)
  {
    return familyRepository.findFamilyById(id);
  }

  @Override
  public void save(Family family)
  {
    familyRepository.saveAndFlush(family);
  }
}
