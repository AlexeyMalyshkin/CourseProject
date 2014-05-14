package com.malyshkin.service.impl;

import com.malyshkin.entity.Role;
import com.malyshkin.repository.RoleRepository;
import com.malyshkin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public void save(Role role) {
        roleRepository.saveAndFlush(role);
    }
}
