package com.malyshkin.config;

import com.malyshkin.dao.RoleDaoImpl;
import com.malyshkin.dao.UserDao;
import com.malyshkin.dao.UserDaoImpl;
import com.malyshkin.domain.Role;
import com.malyshkin.domain.User;
import com.malyshkin.service.RoleService;
import com.malyshkin.service.UserDetailsServiceImpl;
import com.malyshkin.service.UserService;
import com.malyshkin.service.UserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
@EnableTransactionManagement()
@PropertySource("classpath:test.properties")
@ComponentScan("com.malyshkin")
@EnableWebMvc
//@Import({SecurityConfig.class})
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

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword(StringUtils.EMPTY);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        return new LocalSessionFactoryBean() {{
            setDataSource(dataSource());
            setAnnotatedClasses(new Class[]{User.class, Role.class});
            setHibernateProperties(new Properties() {{
//                setProperty("hibernate.hbm2ddl.auto", "create");
                setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
                setProperty("hibernate.show_sql", "true");
            }});
        }};
    }

    @Bean
    public HibernateTransactionManager txManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

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