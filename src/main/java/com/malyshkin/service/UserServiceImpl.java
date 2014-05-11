package com.malyshkin.service;

import com.malyshkin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

//import com.malyshkin.domain.User;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails getUser(String login) {
//        System.out.println("1111111111");

//        Iterable<com.malyshkin.domain.User> list = userRepository.findAll();

        com.malyshkin.domain.User user = new com.malyshkin.domain.User();
        user.setLogin("admin");
        user.setPassword("d033e22ae348aeb5660fc2140aec35850c4da997");
        userRepository.save(user);

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("USER"));

        return new User(user.getLogin(),
                user.getPassword(),
                roles);
    }
}
