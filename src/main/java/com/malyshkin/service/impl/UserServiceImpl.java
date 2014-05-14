package com.malyshkin.service.impl;

import com.malyshkin.entity.Role;
import com.malyshkin.entity.RoleType;
import com.malyshkin.entity.User;
import com.malyshkin.repository.RoleRepository;
import com.malyshkin.repository.UserRepository;
import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;


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
        createAdminUserTemp();

        User user = userRepository.findByEmail(email);

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(RoleType.USER.name()));

        return new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getPassword(),roles);
    }


    private void createAdminUserTemp(){
        Role role = new Role();
        role.setName("ADMIN");
        roleRepository.saveAndFlush(role);



        User userTemp = new User();
        userTemp.setEmail("admin");
        userTemp.setPassword(shaPasswordEncoder.encodePassword("admin", null));
        userTemp.setRole(role);

        userRepository.saveAndFlush(userTemp);


        User userTemp2 = new User();
        userTemp2.setEmail("admin@gmail.com");
        userTemp2.setPassword(shaPasswordEncoder.encodePassword("admin", null));
        userTemp2.setRole(role);

        userRepository.saveAndFlush(userTemp2);
    }


}
