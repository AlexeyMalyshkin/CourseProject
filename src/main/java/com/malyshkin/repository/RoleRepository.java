package com.malyshkin.repository;

import com.malyshkin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
    public Role findByName(String roleName);
}
