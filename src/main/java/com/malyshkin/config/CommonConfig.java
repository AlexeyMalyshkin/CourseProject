package com.malyshkin.config;

import com.malyshkin.dao.RoleDaoImpl;
import com.malyshkin.dao.UserDao;
import com.malyshkin.dao.UserDaoImpl;
import com.malyshkin.service.security.UserDetailsServiceImpl;
import com.malyshkin.service.UserService;
import com.malyshkin.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.malyshkin")
@EnableWebMvc
public class CommonConfig {

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public RoleDaoImpl roleDao() {
        return new RoleDaoImpl();
    }

//    @Bean
//    public RoleService roleService(){
//        return new RoleService();
//    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }
}