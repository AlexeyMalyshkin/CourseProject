package com.malyshkin.service.impl;

import com.malyshkin.controller.TempDbFiller;
import com.malyshkin.entity.User;
import com.malyshkin.repository.UserRepository;
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

    //Temp:
    @Autowired
    private TempDbFiller tempDbFiller;

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
        tempDbFiller.fillDbWithTempData();

        User user = userRepository.findByEmail(email);

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), roles);
    }
}
