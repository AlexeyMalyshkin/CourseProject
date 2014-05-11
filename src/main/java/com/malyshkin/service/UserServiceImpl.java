package com.malyshkin.service;

import com.malyshkin.entity.RoleType;
import com.malyshkin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails getUser(String login) {
        com.malyshkin.entity.User user = new com.malyshkin.entity.User();
        user.setLogin("admin");
        user.setPassword("d033e22ae348aeb5660fc2140aec35850c4da997");
        userRepository.save(user);

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(RoleType.ADMIN.name()));

        return new User(user.getLogin(),
                user.getPassword(),
                roles);
    }
}
