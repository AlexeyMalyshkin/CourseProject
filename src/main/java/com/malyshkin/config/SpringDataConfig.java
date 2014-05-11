package com.malyshkin.config;

import com.malyshkin.entity.User;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.malyshkin.repository")
public class SpringDataConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean lcem = new LocalContainerEntityManagerFactoryBean();

        lcem.setDataSource(dataSource);
        lcem.setPackagesToScan(User.class.getPackage().getName());
        lcem.setPersistenceProvider(new HibernatePersistenceProvider());
        lcem.setJpaProperties(getHibernateProperties());
        lcem.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return lcem;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    private Properties getHibernateProperties(){
        return new Properties() {{
                put("hibernate.hbm2ddl.auto", "create");
                put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
                put("hibernate.show_sql", "true");
            }};
    }
}

