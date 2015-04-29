package com.malyshkin.service;

import com.malyshkin.entity.Family;

public interface FamilyService
{
  public Family findFamily(long id);
  public void save(Family family);
}
