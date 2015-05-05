package com.malyshkin.repository.specification;

import com.malyshkin.entity.FamilyInvite;
import com.malyshkin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FamilyInviteRepository extends JpaRepository<FamilyInvite, Long>
{
  public FamilyInvite findFamilyIniviteByFrom(@Param("from") User user);

  public List<FamilyInvite> findFamilyInvitesByFrom(@Param("from") User user);

  public FamilyInvite findFamilyIniviteByTo(@Param("to") User user);

  public List<FamilyInvite> findFamilyInvitesByTo(@Param("to") User user);
}
