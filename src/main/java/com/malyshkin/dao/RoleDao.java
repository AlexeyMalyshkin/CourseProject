package com.malyshkin.dao;

import com.malyshkin.domain.Role;

public interface RoleDao {

    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);

    String findNameById(long id);

}