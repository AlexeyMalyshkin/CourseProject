package com.malyshkin.service.impl;

import com.malyshkin.controller.TempDbFiller;
import com.malyshkin.entity.Role;
import com.malyshkin.entity.RoleType;
import com.malyshkin.entity.User;
import com.malyshkin.repository.UserRepository;
import com.malyshkin.service.RoleService;
import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static boolean FIRST_TIME_CREATE_DB = true;
    //Temp:
    @Autowired
    private TempDbFiller tempDbFiller;

    @Autowired
    RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails findUserDetails(String email) {
        populateTestData();

        User user = userRepository.findByEmail(email);
        Role role = roleService.findByName(RoleType.USER.name());
        user.setRole(role);

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), roles);
    }

    private void populateTestData() {
        if (FIRST_TIME_CREATE_DB) {
            tempDbFiller.fillDbWithTempData();
        }
        FIRST_TIME_CREATE_DB = false;
    }

}
