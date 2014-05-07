package com.malyshkin.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableTransactionManagement()
@PropertySource("classpath:test.properties")
public class DataSourceConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("url"));
//        dataSource.setUrl("jdbc:h2:~/Course project/test;");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword(StringUtils.EMPTY);

        return dataSource;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        return new LocalSessionFactoryBean() {{
//            setDataSource(dataSource());
//            setAnnotatedClasses(new Class[]{User.class, Role.class});
//            setHibernateProperties(new Properties() {{
////                setProperty("hibernate.hbm2ddl.auto", "create");
//                setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//                setProperty("hibernate.show_sql", "true");
//            }});
//        }};
//    }

}
