package com.malyshkin.config;

import com.malyshkin.service.CategoryService;
import com.malyshkin.service.RoleService;
import com.malyshkin.service.TransactionService;
import com.malyshkin.service.UserService;
import com.malyshkin.service.impl.CategoryServiceImpl;
import com.malyshkin.service.impl.RoleServiceImpl;
import com.malyshkin.service.impl.TransactionServiceImpl;
import com.malyshkin.service.impl.UserServiceImpl;
import com.malyshkin.service.security.UserDetailsServiceImpl;
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
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public RoleService roleService(){
        return new RoleServiceImpl();
    }

    @Bean
    public CategoryService categoryService(){ return new CategoryServiceImpl();}

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public TransactionService transactionService(){ return new TransactionServiceImpl();}
}