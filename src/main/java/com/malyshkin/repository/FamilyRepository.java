package com.malyshkin.repository;

import com.malyshkin.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FamilyRepository extends JpaRepository<Family,Long>
{
  Family findFamilyById(@Param("id") long id);
}
