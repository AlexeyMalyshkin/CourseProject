package com.malyshkin.repository.specification;

import com.malyshkin.entity.FamilyInvite;
import com.malyshkin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FamilyInviteRepository extends JpaRepository<FamilyInvite, Long>
{
  public FamilyInvite findFamilyIniviteByUser(@Param("user") User user);

  public List<FamilyInvite> findFamilyInvitesByUser(@Param("user") User user);
}
