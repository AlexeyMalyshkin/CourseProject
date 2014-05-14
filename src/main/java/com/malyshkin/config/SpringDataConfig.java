package com.malyshkin.config;

import com.malyshkin.entity.User;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.malyshkin.repository")
@PropertySource("classpath:hibernate.properties")
public class SpringDataConfig {

    @Autowired
    Environment env;

    @Autowired
    private DataSource dataSource;

    private static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String DIALECT = "hibernate.dialect";
    private static final String SHOW_SQL = "hibernate.show_sql";
    private static final String LAZY_LOAD_NO_TRANS = "hibernate.enable_lazy_load_no_trans";

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
                put(HBM2DDL_AUTO, env.getProperty(HBM2DDL_AUTO));
                put(DIALECT, env.getProperty(DIALECT));
                put(SHOW_SQL, env.getProperty(SHOW_SQL));
                put(LAZY_LOAD_NO_TRANS, env.getProperty(LAZY_LOAD_NO_TRANS));
            }};
    }
}

