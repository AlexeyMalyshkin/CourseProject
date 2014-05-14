package com.malyshkin.service;

import com.malyshkin.entity.Role;

public interface RoleService {
    public Role findByName(String roleName);
    public void save(Role role);
}
