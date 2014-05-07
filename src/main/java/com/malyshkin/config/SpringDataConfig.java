package com.malyshkin.config;

import com.malyshkin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
//@ComponentScan("com.malyshkin")
@EnableJpaRepositories("com.malyshkin.repository")
public class SpringDataConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);//???
        jpaVendorAdapter.setDatabase(Database.H2);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {

        LocalContainerEntityManagerFactoryBean lcem = new LocalContainerEntityManagerFactoryBean();
        lcem.setDataSource(dataSource);
        lcem.setPackagesToScan(User.class.getPackage().getName());
        lcem.setJpaVendorAdapter(jpaVendorAdapter());

        return lcem;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }
}
