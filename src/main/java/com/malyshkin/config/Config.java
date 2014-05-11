package com.malyshkin.config;

import com.malyshkin.dao.RoleDaoImpl;
import com.malyshkin.dao.UserDao;
import com.malyshkin.dao.UserDaoImpl;
import com.malyshkin.service.RoleService;
import com.malyshkin.service.UserDetailsServiceImpl;
import com.malyshkin.service.UserService;
import com.malyshkin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableTransactionManagement()
@PropertySource("classpath:test.properties")
@ComponentScan("com.malyshkin")
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
//        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
//        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/**").addResourceLocations("/");
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        return new InternalResourceViewResolver() {{
            setPrefix("/WEB-INF/views/");
            setSuffix(".jsp");
        }};
    }

//    @Bean
//    public HibernateTransactionManager txManager(SessionFactory sessionFactory) {
//        return new HibernateTransactionManager(sessionFactory);
//    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public RoleDaoImpl roleDao() {
        return new RoleDaoImpl();
    }

    @Bean
    public RoleService roleService(){
        return new RoleService();
    }


    ///new :

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

}