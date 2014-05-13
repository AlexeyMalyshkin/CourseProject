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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @Autowired
    ShaPasswordEncoder shaPasswordEncoder;

    @Override
    public UserDetails findUserDetails(String login, String password) {
        User user = userRepository.findByLoginAndPassword(login, shaPasswordEncoder.encodePassword(password, 256));

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(), new ArrayList<GrantedAuthority>(){{
            new SimpleGrantedAuthority(user.getRole().getName());
        }});
    }

    @Override
    public UserDetails findUserDetails(String login) {
        createAdminUserTemp();

        User user = userRepository.findByLogin(login);

//        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>(){{
//            add(new SimpleGrantedAuthority(user.getRole().getName()));
//        }};

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(RoleType.USER.name()));



        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(),roles);

//        return new org.springframework.security.core.userdetails.User(user.getLogin(),
//                user.getPassword(), new ArrayList<GrantedAuthority>(){{
//            new SimpleGrantedAuthority(user.getRole().getName());
//        }});

        return userDetails;
    }

    @Override
    public User findUser(String login, String password) {
        createAdminUserTemp();

        return userRepository.findByLoginAndPassword(login,shaPasswordEncoder.encodePassword(password, null));
    }

    private void createAdminUserTemp(){

        Role role = new Role();
        role.setName("ADMIN");
        roleRepository.saveAndFlush(role);


        User userTemp = new User();
        userTemp.setLogin("admin");
        userTemp.setPassword(shaPasswordEncoder.encodePassword("admin", null));
        userTemp.setEmail("test");
        userTemp.setRole(role);

        userRepository.saveAndFlush(userTemp);
    }


}
