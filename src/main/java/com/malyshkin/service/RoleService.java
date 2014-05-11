package com.malyshkin.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.malyshkin.dao.RoleDao;
import com.malyshkin.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleService {
    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void create(Role role) {
        roleDao.create(role);
    }

    public void update(Role role) {
        roleDao.update(role);
    }

    public void remove(Role role) {
        roleDao.remove(role);
    }

    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    public String findNameById(long id) {
        return findNameById(id);
    }

}
